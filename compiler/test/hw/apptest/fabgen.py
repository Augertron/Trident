#!/bin/evn python2

import fabric
import shlex
import struct

#
# notes how to convert a string float into a
# hex float
#
# "%X" %struct.unpack("!i",struct.pack("!f",float("1.0")))
#



class Xd1Fabric(fabric.Fabric):
    def __init__(self, file, sf, mf):
        stream = open(file,"w")
        fabric.Fabric.__init__(self,stream)
        self.simple = sf
        self.mem = mf
        #
        # set some info so that create can make the right stuff
        #
        self.create()

    def create(self):
        name = self.simple.name
        self.generateHeader("%s test" % name)

        # write inputs
        # delay
        # check outputs
        
        for l in self.simple. list:
            cmd, var, values = l
            if cmd == "write":
                self.createWrite(var, values)
            elif cmd == "read":
                self.createRead(var, values)
            elif cmd == "wait":
                self.delay(int(values))
            else:
                raise Exception('unknown fabric.in command '+l)

        self.generateFooter(100,"End of %s test" % name)


    def fixValues(self, values, width):
        # the goal is to creat something we can use.
        result = []
        for v in values:
            if v.find('.') != -1:
                if v.find('d') == -1:
                    # float -- what about doubles?
                    val = struct.unpack("!i",struct.pack("!f",float(v)))[0]
                else:
                    val= struct.unpack("!Q",struct.pack("!d",float(v[:-1])))[0] 
            elif v.find('x') != -1:
                val = long(v,16)
            else:
                val = long(v)
                if val < 0:
                    # change signed to unsigned
                    # this could be better.  I should probably convert
                    # everything to long long and then mask off the proper
                    # number of bits.
                    if width == 32:
                        val = struct.unpack("!I", struct.pack("!i",val))[0]
                    elif width == 64:
                        val = struct.unpack("!Q", struct.pack("!q",val))[0]
                    else:
                        val = struct.unpack("!Q", struct.pack("!q",val))[0]
                        
                    
            result.append(val)
        if len(values) != len(result):
            raise Exception('fixValues() lost a value somehow')
        return result

    def getAddress(self, var):
        #print " addr ",self.mem.getAddress(var)
        return long(self.mem.getAddress(var),16)
            
    def createWrite(self, var, values):
        v = self.fixValues(values, self.mem.getWidth(var))
        if var.find('.') == -1:
            addr = self.getAddress(var)
        else:
            if var == ".start":
                addr = self.getAddress('start')
            elif var == ".done":
                raise Exception('You cannot write done.')
            elif var == ".reset":
                # print ignore reset???
                return
            else:
                raise Exception('Unrecognized internal varname %s '% var)
            
        rest = ""
        if len(values) > 1: rest = "..."
            
        self.writeInputs(v,addr,"write %s %s %s"%(var, values[0], rest))

    def createRead(self, var, values):
        v = self.fixValues(values, self.mem.getWidth(var))
        if var.find('.') == -1:
            addr = self.getAddress(var)
        else:
            if var == ".start":
                raise Exception('You cannot read start.')
            elif var == ".done":
                # this should work ...
                print "This should work ..."
                return
            elif var == ".reset":
                raise Exception('You cannot read reset.')
            else:
                raise Exception('Unrecognized internal varname %s '% var)
        self.readInputs(v,addr,"read %s %s " %(var, values[0]))

        
class Xd1TestDo:
    def __init__(self, file, sf):
        self.stream = fabric.WritelnDecorator(open(file,"w"))
        self.simple = sf
        #
        # set some info so that create can make the right stuff
        #
        self.create()

    def create(self):
        self.stream.writeln("set StdArithNoWarnings 1")
        self.stream.writeln("run "+self.simple.run)
        self.stream.close()


class MemInfo:
    def __init__(self, name, width=-1, depth=-1, address=0x0, index=-1):
        self.name = name
        self.width = width
        self.depth = depth
        self.address = address
        self.index = index

    def getAddrHexStr(self):
        return "0x%X" % str(address)

    def __repr__(self):
        return "%s %i width %i depth %i addr %s" % \
               (self.name, self.index, self.width, self.depth, self.address)


class MemInfoFile:
    def __init__(self, file):
        # try ??
        self.file = open(file,"r")
        self.name = "default"
        self.target = "none"
        self.list = []
        self.dict = {}
        self.START = "("
        self.END = ")"
        
        self.parse()
        #print " hash ",self.dict
        #print " list ",self.list

        
    def getList(self):
        return self.list

    def getAddress(self, a):
        #print " var ",a
        if self.dict.has_key(a):
            return self.dict[a].address
        else:
            raise Exception('Unknown addess variable %s' % a)

    def getWidth(self, a):
        if self.dict.has_key(a):
            return self.dict[a].width
        elif a == ".start":
            return 1
        elif a == ".done":
            return 1
        elif a == ".reset":
            return 1
        else:
            raise Exception('MemInfoFile: getWidth(): Unknown variable %s'% a)

    def getDepth(self, a):
        if self.dict.has_key(a):
            return self.dict[a].depth
        else:
            raise Exception('Unknown addess variable %s' % a)
    

    def start(self):
        tok = self.getToken()
        if tok != self.START:
            raise Exception('parse exception start', 'eggs')


    def end(self):
        tok = self.getToken()
        if tok != self.END:
            raise Exception('parse exception end', 'eggs')

    def getToken(self):
        tok = self.sh.get_token()
        #print " current ",tok
        return tok
    
    def parse(self):
        self.sh = shlex.shlex(self.file)

        self.start()
        tok = self.getToken()
        if tok != "Config":
            raise Exception("Unknown file type",'eggs')

        tok = self.getToken()
        while (tok == self.START):
          tok = self.getToken()
          if tok == "design":
              self.parseDesign()
          elif tok == "registers":
              self.parseRegisters()
          elif tok == "memory":
              self.parseMemory()
          elif tok == "target":
              self.parseTarget()
          else:
              raise Exception('unknown token %s ' % tok)
          tok = self.getToken()

    def parseDesign(self):
        tok = self.getToken()
        self.name = tok
        self.end()

    def parseTarget(self):
        tok = self.getToken()
        self.target = tok
        self.end()

    def parseRegisters(self):
        tok = self.getToken()
        while (tok == self.START):
            tok = self.getToken()
            mem_info = None
            if tok == "register":
                mem_info = self.parseRegister()
            else:
                print "broken ..."
            if mem_info:
                self.list.append(mem_info)
                self.dict[mem_info.name] = mem_info
            tok = self.getToken()
        if tok != self.END:
            print "broken ..,"
                    

    def parseRegister(self):
        tok = self.getToken()
        mem = MemInfo(tok)
        tok = self.getToken()
        while (tok == self.START):
            tok = self.getToken()
            if tok == "width":
                mem.width = int(self.getToken())
                self.end()
            elif tok == "depth":
                mem.depth = int(self.getToken())
                self.end()
            elif tok == "address":
                mem.address = self.getToken()
                self.end()
            elif tok == "index":
                mem.index = int(self.getToken())
                self.end()
            else:
                print "broke..."
            # get next start
            tok = self.getToken()
        if tok != self.END:
            print "broken ..."
        return mem
        
    def parseMemory(self):
        tok = self.getToken()
        while (tok == self.START):
            tok = self.getToken()
            mem_info = None
            if tok == "array":
                # sly?
                mem_info = self.parseRegister()
            else:
                print "broken ..."
            if mem_info:
                self.list.append(mem_info)
                self.dict[mem_info.name] = mem_info
            tok = self.getToken()
        if tok != self.END:
            print "broken ..,"



    
class SimpleFile:
    def __init__(self, file):
        # try ??
        self.file = open(file,"r")
        self.list = []
        self.run = "5 us"
        self.name = "default"
        self.START = "("
        self.END = ")"
        self.parse()
        # debug !!
        #print self.list

    def getList(self):
        return self.list

    def start(self):
        tok = self.getToken()
        if tok != self.START:
            raise Exception('parse exception start', 'eggs')

    def end(self):
        tok = self.getToken()
        if tok != self.END:
            raise Exception('parse exception end', 'eggs')

    def getToken(self):
        tok = self.sh.get_token()
        #print " current ",tok
        return tok
    
    def parse(self):
        self.sh = shlex.shlex(self.file)
        self.sh.wordchars = self.sh.wordchars+'.-'
        
        self.start()
        tok = self.getToken()
        if tok != "fabric.in":
            raise Exception("Unknown file type",'eggs')
        tok = self.getToken()
        # what if there is no name?
        self.name = tok
        
        tok = self.getToken()
        while (tok == self.START):
          tok = self.getToken()
          if tok == "write":
              name, value = self.parseWrite()
              self.list.append(('write',name,value))
          elif tok == "read":
              name, value = self.parseRead()
              self.list.append(('read',name,value))
          elif tok == "wait":
              type, length = self.parseWait()
              self.list.append(('wait',type,length))
          elif tok == "run":
              self.run = self.parseRun()
          else:
              raise Exception('parse exception unkown token '+tok) 
          tok = self.getToken()

    def parseWrite(self):
        tok = self.getToken()
        name = tok
        value = []
        tok = self.getToken()
        while (tok != self.END):
            value.append(tok)
            tok = self.getToken()

        return name, value

    def parseRead(self):
        return self.parseWrite()
    
    def parseWait(self):
        type = self.getToken()
        time = self.getToken()
        if time == self.END:
            time = 0
        else:
            self.end()
        return type,time

    def parseRun(self):
        time = self.getToken()
        string = self.getToken()
        if string == self.END:
            string = "us"
        else:
            self.end()
        return time+" "+string
        

    

def printUsage():
    print 'usage: fabgen.py'
    print '-i mem_info'
    print '-s simple.in'
    print '-o output'


def parseCmdLine():
    import sys
    import getopt

    try:
        opts, args = getopt.getopt(sys.argv[1:], 'i:s:o:')
    except getopt.error, msg:
        print msg
        print ''
        printUsage()
        sys.exit(2)

    if len(opts) < 2:
        printUsage()
        sys.exit(2)
        
    filename = 'fabric.in'
    mem_info = None
    simple = None
    for o,a in opts:
        if o == '-o' : filename = a
        if o == '-i' : mem_info = a
        if o == '-s' : simple = a

    return filename,mem_info, simple


def main():
    filename, info, simple = parseCmdLine()
    #print "Create fabric"
    if not info:
        print "Must have meminfo file"
        printUsage()
        return
    else:
        mif = MemInfoFile(info)

    if not simple:
        print "Must have simple.in"
        printUsage()
        return
    else:
        sf = SimpleFile(simple)

    fab = Xd1Fabric(filename, sf, mif)
    quick = Xd1TestDo("test.do", sf)


if __name__ == '__main__':
    main()
    

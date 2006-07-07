import sys, struct

class WritelnDecorator:
    """Used to decorate file-like objects with a handy 'writeln' method"""
    def __init__(self,stream):
        self.stream = stream

    def __getattr__(self, attr):
        return getattr(self.stream,attr)

    def writeln(self, *args):
        if args: apply(self.write, args)
        self.write('\n') # text-mode streams translate to \r\n if needed


class Fabric:
    def __init__(self, stream=sys.stdout):
        self.stream = WritelnDecorator(stream)

    def paddZeros(self, values, size):
        rs = RoadSegment()
        rs.genZero(size)
        values.extend(rs.getList())

    def delay(self, amount):
        self.stream.writeln("D %d" % amount)

    def comment(self, string):
        self.stream.writeln("# %s" % string)

    def message(self, string):
        self.stream.writeln("P %s" % string)

    def stop(self):
        self.stream.writeln("S")

    def valueToString(self, value):
        return value
    
    def readByte(self, addr, value, comment):
        self.stream.writeln("R %010X %016X FF 0 1 # %s" % \
                            (addr, self.valueToString(value), comment))

    def readBlock(self, addr, values):
        total = len(values)
        count = 0
        for i in values:
            if count is 0:
                self.stream.writeln("R %010X %016X FF 0 %X" % \
                                    (addr, self.valueToString(i), (total*2)-1))
            else:
                self.stream.writeln("B %016X FF" % (self.valueToString(i)))
            count +=1

    def writeByte(self, addr, value, comment):
        self.stream.writeln("W %010X %016X FF 0 1 # %s" % \
                            (addr, self.valueToString(value), comment))

    def writeBlock(self, addr, values):
        total = len(values)
        # can we fix this?
        if total > 8:
            print "writeBlock too big!! ",total
            sys.exit(-1)
        count = 0
        for i in values:
            if count is 0:
                self.stream.writeln("W %010X %016X FF 0 %X" % \
                                    (addr, self.valueToString(i),(total*2)-1))
            else:
                self.stream.writeln("B %016X FF" % self.valueToString(i))
            count += 1
            
    def generateHeader(self, message, delay=100):
        if delay > 0:
            self.comment("Insert a delay to allow the DLL's some time to lock")
            self.delay(delay)
        self.comment("")
        self.message(message)


    def writeInputs(self, values, data_addr, message):
        addr = data_addr
        self.message(message)
        total = len(values)
        chunks = total / 8
        mod = total % 8
        for index in range(chunks):
            slice = values[index*8:(index+1)*8]
            self.writeBlock(addr, slice)
            addr += 0x40
        if (mod is not 0):
            slice = values[chunks*8:total]
            self.writeBlock(addr, slice)
            addr += mod * 8;
        return addr


    def readInputs(self, values, data_addr, message):
        addr = data_addr
        self.message(message)
        total = len(values)
        chunks = total / 8
        mod = total % 8
        for index in range(chunks):
            slice = values[index*8:(index+1)*8]
            self.readBlock(addr, slice)
            addr += 0x40
        if (mod is not 0):
            slice = values[chunks*8:total]
            self.readBlock(addr, slice)
            addr += mod * 8;
        return addr


    


    def generateFooter(self, delay, string):
        self.comment("")
        if (delay > 0):
            self.delay(delay)
        self.message(string)
        self.stop()
        self.comment("End of test")

        

    def create(self):
        pass

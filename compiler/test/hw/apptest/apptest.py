#!/bin/env python2

import os, shutil
import unittest
from unittest import *
from string import *
import re
import sys,time

import pexpect

class Test:
    def __init__(self, name):
        self.name = name
        self.result = 0

    def init(self):
        pass

    def run(self):
        pass

    def getResult(self):
        return self.result

    def printResult(self):
        print " Result ",self.result

    def clean(self):
        pass

    def getName(self):
        return self.name


TIMEOUT=120

def setTimeout(timeout):
    global TIMEOUT
    if timeout <= 0:
        TIMEOUT=None

    else:
        TIMEOUT=timeout

def getTimeout():
    global TIMEOUT
    return TIMEOUT

class ExecTest(Test):
 
    def __init__(self, name, top, design, dir, options={}, debug=None):
        Test.__init__(self, name)
        self.top = top
        
        self.setDesign(design)
        self.setTestbench()

        self.dir = dir
        self.options = options
        self.debug = debug
        self.log = ""
        self.logname = ""

    def setTestbench(self):
        tb = self.design.replace(self.design_base,"tb_%s.in" % self.design_noc)
        if tb not in TB_LIST:
            raise MissingException("corresponding tb*.in file", tb)
        self.tb = tb
        self.tb_base = os.path.basename(tb)
        self.tb_dir = os.path.dirname(tb)
        self.tb_nointb = replace(replace(self.tb_base, ".in",""), "tb_","")
        
    def setDesign(self, design):
        self.design = design
        self.design_base = os.path.basename(design)
        self.design_dir = os.path.dirname(design)
        self.design_noc = replace(self.design_base,".c","")

    def setOption(self, opt, option):
        if not self.options:
            self.options = {}
        self.options[opt] = option

    def getOption(self, opt):
        if self.options.has_key(opt):
            return self.options[opt]
        else:
            return None

    def setLog(self, file):
        self.log = ">> %s.out 2>&1" % file
        self.logname = "%s.out" % file

    def clearLog(self):
        if os.path.exists(self.logname):
            os.remove(self.logname)
        else:
            pass
            #print " no log %s " % (self.logname)


    def system(self,cmd, cmd_list=[], **kwds):
        #print "cmd ",cmd 
        p = pexpect.spawn("sh", ["-c", cmd], time)
        #timeout=getTimeout() in the arguments
        #doesn't work because it reads the TIMEOUT
        #variable before it can be set.
        if 'timeout' in kwds.keys():
            system_timeout=kwds['timeout']
        else:
            system_timeout=getTimeout()
        try:
            p.expect(pexpect.EOF, timeout=system_timeout)
        except pexpect.TIMEOUT,msg:
            # I could update some thing in the log
            # that is why I should log here rather than
            # anywhere else.
            pass
            
        
        if (p.isalive()):
            p.kill(9)
            results = -1
        else:
            results = p.exitstatus

        #print p.before

        return results
        

DESIGN=""
DESIGN_LIST=[]
DESIGN_DIR="build/design"
TB_LIST=[]
DESIGN_OPTIONS=""

def getDesignDir():
    return DESIGN_DIR

def setDesignDir(dir):
    global DESIGN_DIR
    DESIGN_DIR = dir

def getDesign():
    global DESIGN
    #print "getDesign ",DESIGN
    return DESIGN

def nextDesign():
    global DESIGN, DESIGN_LIST
    DESIGN=DESIGN_LIST[DESIGN_LIST.index(DESIGN)+1]

def getOptions():
    global DESIGN_OPTIONS
    return DESIGN_OPTIONS

def setOptions(input):
    global DESIGN_OPTIONS
    DESIGN_OPTIONS=input


def visit(arg, dirname, names):
    for name in names:
        #print dirname," ",name
        design, ext = os.path.splitext(name)
        #print design," ext -%s-" % ext
        if find(ext,'.c') != -1 and len(ext) is 2:
            #print "appending ",name
            bigname = os.path.join(dirname, name)
            if arg[1]:
                if bigname.find(arg[1]) != -1:
                    arg[0].append(bigname)
            else:
                arg[0].append(bigname)

def visit_tb(arg, dirname, names):
    for name in names:
        tb, ext = os.path.splitext(name)
        if find(ext, '.in') != -1 and len(ext) is 3:
            bigname = os.path.join(dirname, name)
            if arg[1]:
                if bigname.find(arg[1]) != -1:
                    arg[0].append(bigname)
            else:
                arg[0].append(bigname)
                

def initDesign(grep):
    global DESIGN_LIST,DESIGN
    DESIGN_LIST = []

    os.path.walk('examples', visit, (DESIGN_LIST, grep))

    #print "Designs ",DESIGN_LIST
    print "Design Count",len(DESIGN_LIST)
    
    # probably should have test.
    if len(DESIGN_LIST) > 0:
        DESIGN=DESIGN_LIST[0]
        
def initTestbench(grep):
    global TB_LIST
    TB_LIST = []

    os.path.walk('examples', visit_tb, (TB_LIST, grep))



def setupLLVM():
    if not os.environ.has_key('LLVM_VER'):
        llvm_ver="llvm-1.5"
        llvm_top="/home/rasr/apps/llvm/%s" % llvm_ver
        llvm_gccdir="%s/cfrontend/x86/llvm-gcc" % llvm_top
        path = os.environ['PATH']
    
        os.environ['LLVM_VER']=llvm_ver
        os.environ['LLVM_TOP']=llvm_top
        os.environ['LLVMGCCDIR']=llvm_gccdir
        os.environ['LLVM_LIB_SEARCH_PATH']=llvm_gccdir+"/bytecode-libs"
        os.environ['PATH']=path+os.pathsep+llvm_top+"/obj/Debug/bin"


def setupRiviera():
    if not os.environ.has_key('ALDEC_LICENSE_FILE'):
        #print "setup riviera"
        aldec_path="/home/rasr/pkgs/riviera-2005.04a"
        path=os.environ['PATH']
        
        if os.environ.has_key('LD_LIBRARY_PATH'):
            ld_lib_path=os.environ.has_key('LD_LIBRARY_PATH')
        else:
            ld_lib_path=None
    
        if ld_lib_path is None:
            os.environ['LD_LIBRARY_PATH']=".:%s/bin" % aldec_path
        else:
            os.environ['LD_LIBRARY_PATH']=".:%s/bin:%s" % \
                                       (aldec_path, ld_lib_path)
        os.environ['ALDEC_LICENSE_FILE']="27001@lasshaar.lanl.gov"
        os.environ['PATH']=aldec_path+"/bin"+os.pathsep+path
        os.environ['VSIMSACFG']=aldec_path
        #result = os.system('ulimit -s 32768')


def setupClassPath(top):
    classpath = os.environ['CLASSPATH']
    if not os.environ.has_key('OLD_CLASSPATH'):
        os.environ['OLD_CLASSPATH'] = classpath
        os.environ['CLASSPATH'] = "%s:%s" % \
                                  ( classpath, top+"/build")
        #print " class path ",os.environ['CLASSPATH']
        
class MissingException(Exception):
    def __init__(self, message, test):
        self.msg = message
        self.test = test
    def __str__(self):
        return repr("Missing %s %s" %(self.msg, self.test));

class SimulationException(Exception):
   def __init__(self, message, test):
        self.msg = message
        self.test = test
   def __str__(self):
        return repr("Simulation %s %s" %(self.msg, self.test));
 
class CompilationException(Exception):
   def __init__(self, message, test):
        self.msg = message
        self.test = test
   def __str__(self):
        return repr("Compilation %s %s" %(self.msg, self.test));
 


        
class ExecTCC(ExecTest):
    def __init__(self, top, design, llv, dir, options={}, debug=None):
        ExecTest.__init__(self, "ExecTCC", top, design, dir, options, debug)
        self.llv = llv
        
        setupClassPath(top)
        setupLLVM()
            
        self.setLog(self.design_noc)

    def run(self):
        cwd = os.getcwd()
        options = ""
        dir = "%s/%s/%s" % ( self.dir, self.design_dir, self.design_noc)

        #print "mkdir ",dir
        if not os.path.exists(dir): 
            os.makedirs(dir)
        shutil.copy(self.design,dir)
        if self.tb:
            shutil.copy(self.tb, dir)
            options+=" -b --bench_input=_%s" % self.tb_base

        os.chdir(dir)
        
        self.clearLog()


        my_options = self.getOption("tcc")
        if my_options:
            options = my_options

        #Search for testbench files

            
        cmd = "%s/tcc --llv=%s %s %s run %s " % \
              (self.top, self.llv, options, self.design_base, \
               self.log)
        #print "cmd ",cmd
        self.result = self.system(cmd)
        os.chdir(cwd)
        return self.result


class ExecVCOM(ExecTest):
    def __init__(self, top, design, dir, libs, options={}, debug=None):
        ExecTest.__init__(self, "ExecVCOM", top, design, dir, options, debug)
        self.libs = libs
        
        self.setLog(self.design_noc+".vhd")

        setupRiviera()
        self.checkLibs()

        
    def checkLibs(self):
        import glob
        
        for lib in self.libs:
            target = "%s/build/%s/%s/%s.lib" % \
                     (self.top, lib[0], lib[0], lib[0])
            # this should test file size
            if os.path.isfile(target):
                continue
            
            dir = "%s/build/%s" % (self.top, lib[0])
            if not os.path.exists(dir):
                os.makedirs(dir)
            shutil.copy(lib[1]+"/build.sh", dir)
            #print " files "+lib[2]+"*.vhd"
            
            files = glob.glob(lib[2]+"/*.vhd")
            if len(files) <= 0:
                print " No files found -- throw exception!"
                continue
            
            for file in files:
                #print " copy file %s to %s " % (file,dir)
                shutil.copy(file, dir)
                
            cwd = os.getcwd()
            os.chdir(self.top)
            os.chdir(dir)
            result = self.system("sh ./build.sh ")
            os.chdir(cwd)


    def mapLibs(self):
        for lib in self.libs:
            self.mapLib(lib[0],"%s/build/%s/%s" % (self.top, lib[0], lib[0]))
            
        
    def mapLib(self, lib, path):
        if os.path.exists(lib):
            shutil.rmtree(lib)
            
        result = self.system("vlib %s %s " % (lib, self.log))
        result = self.system("vmap %s %s %s " % (lib, path, self.log)) 
        

    def run(self):
        cwd = os.getcwd()
        dir = "%s/%s/%s" % ( self.dir, self.design_dir, self.design_noc)
        #print "mkdir ",dir
        if not os.path.exists(dir): 
            os.makedirs(dir)
            # this should be an exception -- if the .vhd is not there
            # I cannot do anything ...
        if not os.path.isfile("%s/%s.vhd" % (dir,self.design_noc)):
            #
            os.chdir(cwd)
            raise MissingException("generated vhdl",self.design);
                
        os.chdir(dir)
        self.clearLog()
        
        self.mapLib("work","work")
        self.mapLibs()

        options=""
        if self.getOption("vcom") :
            options=self.getOption("vcom")
            
        cmd = "vcom %s %s.vhd %s " % \
                        (options, self.design_noc, self.log)
        self.result = self.system(cmd)
        os.chdir(cwd)

        if self.result != 0:
            os.chdir(cwd)
            raise CompilationException("failed",self.design)
        
        return self.result
        

class ExecVSIM(ExecTest):

    def __init__(self, top, design, dir, libs, options={}, debug=None):
        ExecTest.__init__(self, "ExecVSIM", top, design, dir, options, debug)
        self.libs = libs
        
        self.setLog(self.design_noc+".sim")

        self.is_done = re.compile(r'[Dd]one signal(s)? found')
        self.is_err = re.compile(r'ERROR')

    def mapLibs(self):
        for lib in self.libs:
            self.mapLib(lib[0],"%s/build/%s/%s" % (self.top, lib[0], lib[0]))
            
        
    def mapLib(self, lib, path):
        if os.path.exists(lib):
            shutil.rmtree(lib)
            
        result = self.system("vlib %s %s " % (lib, self.log))
        result = self.system("vmap %s %s %s " % (lib, path, self.log)) 
        

    def run(self):
        cwd = os.getcwd()
        dir = "%s/%s/%s" % ( self.dir, self.design_dir, self.design_noc)
        # change the name of the log??
        vsim_out = self.design_noc+".vsim.out"
        
        #print "mkdir ",dir
        if not os.path.exists(dir): 
            os.makedirs(dir)
            # this should be an exception -- if the .vhd is not there
            # I cannot do anything ...
        if not os.path.isfile("%s/%s.vhd" % (dir,self.design_noc)):
            #
            os.chdir(cwd)
            raise MissingException("generated vhdl",self.design);
                        
        design_tb = "tb_%s.vhd" % self.design_noc
        if not os.path.isfile("%s/%s" % (self.design_dir, design_tb)):
            #print "Cannot find test bench ..."
            f=open(os.path.join(dir,vsim_out),"w")
            f.write("Cannot find testbench.")
            f.close()
            os.chdir(cwd)
            raise MissingException("testbench",self.design);
            
            self.result = 1
            return self.result

        #print " copy %s/%s to %s " % (self.design_dir, design_tb, dir)
        shutil.copy("%s/%s" % (self.design_dir, design_tb), dir)
        
        os.chdir(dir)
        self.clearLog()
        
        self.mapLib("work","work")
        self.mapLibs()

        options=""
        if self.getOption("vcom") :
            options=self.getOption("vcom")
            
        cmd = "vcom %s %s.vhd %s " % \
                        (options, self.design_noc, self.log)
        self.result = self.system(cmd)
        
        cmd = "vcom %s tb_%s.vhd %s " % \
              (options, self.design_noc, self.log)
        self.result = self.system(cmd)
        if (self.result != 0):
            os.chdir(cwd)
            raise CompilationException("failed",self.design)
            return self.result
        

        cmd = "ulimit -s 32768; "        
        cmd = cmd + "cat %s " % design_tb
        cmd = cmd + "| egrep -e '-[-]+ @'"
        cmd = cmd + "| cut -d @ -f 2- "
        cmd = cmd + "| vsim > %s 2>&1" % vsim_out
        
        self.system(cmd)

        if not os.path.isfile(vsim_out):
            print "Cannot find %s ",vsim_out
        
        try:
            file = open(vsim_out, "r")
        except IOError, msg:
            print file, ":", msg
            self.result = -1
            return self.result

        error = None
        done = None
        lines = file.readlines()
        for line in lines:
            if done is None:
                match = self.is_done.search(line)
                if match is not None:
                    done = True
            if error is None:
                match = self.is_err.search(line)
                if match is not None:
                    error = True
        file.close()
        os.chdir(cwd)

        if done is not None and error is None:
            #print "Done is found and no errors"
            self.result = 0
        else:
            #print "Errors found",error," or no done",done
            self.result = -1
            # capture error or lack of done ???
            if (done is None):
                if (error is None):
                    raise SimulationException("done not found", self.design)
                else:
                    raise SimulationException("error found", self.design)
            else:
                raise SimulationException("error found", self.design)
                
        return self.result


class ExecSetupXD1(ExecTest):
    def __init__(self, top, design, dir, libs, options={}, debug=None):
        ExecTest.__init__(self, "ExecSetupXD1", top, design, dir, options, debug)
        self.libs = libs
        
        self.setLog(self.design_noc+".vhd")

        self.is_err = re.compile(r'ERROR')
        self.is_done = re.compile(r'Statistics Report')

        setupRiviera()
        self.checkLibs()


    def checkLibs(self):
        import glob
        
        for lib in self.libs:
            target = "%s/build/%s/%s/%s.lib" % \
                     (self.top, lib[0], lib[0], lib[0])
            # this should test file size
            if os.path.isfile(target):
                continue
            
            dir = "%s/build/%s" % (self.top, lib[0])
            if not os.path.exists(dir):
                os.makedirs(dir)
            shutil.copy(lib[1]+"/build.sh", dir)
            #print " files "+lib[2]+"*.vhd"
            
            files = glob.glob(lib[2]+"/*.vhd")
            if len(files) <= 0:
                print " No files found -- throw exception!"
                continue
            
            for file in files:
                #print " copy file %s to %s " % (file,dir)
                shutil.copy(file, dir)
                
            cwd = os.getcwd()
            os.chdir(self.top)
            os.chdir(dir)
            result = self.system("sh ./build.sh ")
            os.chdir(cwd)


    def mapLibs(self):
        for lib in self.libs:
            self.mapLib(lib[0],"%s/build/%s/%s" % (self.top, lib[0], lib[0]))
            
        
    def mapLib(self, lib, path):
        if os.path.exists(lib):
            shutil.rmtree(lib)
            
        result = self.system("vlib %s %s " % (lib, self.log))
        result = self.system("vmap %s %s %s " % (lib, path, self.log)) 


    def run(self):
        cwd = os.getcwd()
        dir = "%s/%s/%s" % ( self.dir, self.design_dir, self.design_noc)
        # change the name of the log??
        vsim_out = self.design_noc+".mk.out"
        
        #print "mkdir ",dir
        if not os.path.exists(dir): 
            os.makedirs(dir)
            # this should be an exception -- if the .vhd is not there
            # I cannot do anything ...
        if not os.path.isfile("%s/%s.vhd" % (dir,self.design_noc)):
            #
            os.chdir(cwd)
            raise MissingException("generated vhdl",self.design);
            
            
        fabric_in = "tb_%s.in" % self.design_noc
        if not os.path.isfile("%s/%s" % (self.design_dir, fabric_in)):
            #print "Cannot find test bench ..."
            f=open(os.path.join(dir,vsim_out),"w")
            f.write("Cannot find fabric.in for file.")
            f.close()
            os.chdir(cwd)
            raise MissingException("fabric.in",self.design);
            self.result = 1
            return self.result
        # copy fabric.in
        shutil.copy("%s/%s" % (self.design_dir, fabric_in),dir)

        #print " copy %s/%s to %s " % (self.design_dir, design_tb, dir)
        template = os.path.join(self.top,'test/hw/template')
        cmd = "rsync -C -rl %s %s/%s/ " % (template, self.top, dir)
        #print "cmd ",cmd
        result = self.system(cmd)

        os.chdir(dir)

        shutil.copy(self.design_noc+".vhd", \
                    "template/hdl/user_app/tcc_design.vhd")

        
        cmd = "python %s/test/hw/apptest/fabgen.py -s %s -i mem.info -o fabric.in" % (self.top,fabric_in)
        #print " cmd %s " % cmd
        result = self.system(cmd)
        # check result ?

        if os.path.isfile("fabric.in"):
            shutil.copy("fabric.in","template/sim/tc_01")
        else:
            os.chdir(cwd)
            raise MissingException("fabric.in",self.design)
            self.result = -1
            return self.result
            
        if os.path.isfile("test.do"):
            shutil.copy("test.do","template/sim/tc_01")
        else:
            os.chdir(cwd)
            raise MissingException("test.do",self.design)
            self.result = -1
            return self.result
                        
        os.chdir('template')

        libs = ''
        for lib in self.libs:
            libs = libs+"%s=%s/build/%s/%s " % \
                   (upper(lib[0]),self.top,lib[0],lib[0])
        cmd = "make %s compile > %s 2>&1" % (libs, vsim_out)
        #print "cmd ",cmd
        self.result = self.system(cmd)

        cmd = "ulimit -s 32768; "      
        cmd = cmd + "make %s sim >> %s 2>&1" % (libs, vsim_out)
        #print "cmd ",cmd
        self.result = self.system(cmd, 240)

        if not os.path.isfile(vsim_out):
            print "Cannot find %s ",vsim_out
        
        try:
            file = open(vsim_out, "r")
        except IOError, msg:
            print file, ":", msg
            self.result = -1
            return self.result

        error = None
        done = None
        lines = file.readlines()
        for line in lines:
            if error is None:
                match = self.is_err.search(line)
                if match is not None:
                    error = True
            if done is None:
                match = self.is_done.search(line)
                if match is not None:
                    done = True
        file.close()
        os.chdir(cwd)

        if error is None and done is not None:
            #print "Done is found and no errors"
            self.result = 0
        else:
            #print "Errors found",error," or no done",done
            self.result = -1
            # capture error or lack of done ???
            if (done is None):
                if (error is None):
                    raise SimulationException("done not found", self.design)
                else:
                    raise SimulationException("error found", self.design)
            else:
                raise SimulationException("error found", self.design)
                

        return self.result

        

class TestAnt(unittest.TestCase):
    def setUp(self):
        dir = getDesignDir()
        if os.path.exists(dir):
            shutil.rmtree(dir)            
        
        os.makedirs(dir)

    def testAnt(self):
       cmd = os.system("ant compile %s" % "> /dev/null 2>&1")
       self.assertEqual(cmd, 0)


class TestInput(unittest.TestCase):
    def setUp(self):
        nextDesign()

    def testInput(self):
        pass

class TestDesignShort(unittest.TestCase):
    def setUp(self):
        self.design = getDesign()

        self.top = os.getcwd()
        #print "top ",self.top
        
        self.llv = self.top + "/build/llv-obj/Debug/bin"
        
        dir = getDesignDir()

        self.options = getOptions()

        self.et = ExecTCC(self.top, self.design, self.llv, dir)
        
        libs = [("quixilica","%s/lib/quixilica" % self.top, \
                 "/home/rasr/pkgs/quixilica/vhdl"),
                ("trident", "%s/lib/trident" % self.top, \
                 "%s/lib/trident" % self.top)]
        self.ev = ExecVCOM(self.top, self.design, dir, libs)
        
        self.es = ExecVSIM(self.top, self.design, dir, libs)

        self.ex = ExecSetupXD1(self.top, self.design, dir, libs)


    def shortDescription(self):
        return getDesign().split('.')[0]+" "+str(self)

    def id(self):
        # this is ugly, but the other guy is splitting on a .
        # swapped examples/merge/merge2/__main__.TestDesignShort.testVCOM
        # is no good, unless I stick myself between the last period and
        # the final bit of text.
        return unittest.TestCase.id(self)+"/"+getDesign().split('.')[0]

    def testVCOM(self):
        options = "-t vhdl %s " % self.options
        self.et.setOption("tcc", options)
        self.et.dir = os.path.join( self.et.dir, "vcom")        
        result = self.et.run()
        self.assertEqual(result, 0)
        
        self.ev.setLog(self.ev.design_noc+".vhd")
        self.ev.dir = os.path.join(self.ev.dir, "vcom")
        result = self.ev.run()
        self.assertEqual(result, 0)

    def testVSIM(self):
        options = "-t vhdl %s " % self.options
        self.et.setOption("tcc", options)
        self.et.dir = os.path.join(self.et.dir, "vsim")        
        result = self.et.run()
        self.assertEqual(result, 0)
        
        self.ev.setLog(self.ev.design_noc+".vhd")
        self.ev.dir = os.path.join(self.ev.dir, "vsim")                
        result = self.ev.run()
        self.assertEqual(result, 0)

        self.es.dir = os.path.join(self.es.dir, "vsim")        
        result = self.es.run()
        self.assertEqual(result, 0)

    def testVSIMXD1(self):
        options = "-t vhdl --top %s " % self.options
        self.et.setOption("tcc", options)
        self.et.dir = os.path.join(self.et.dir,"simxd1")        
        result = self.et.run()
        self.assertEqual(result, 0)
        
        self.ex.setLog(self.ev.design_noc+".xd1.vhd")
        self.ex.dir = os.path.join(self.ev.dir,"simxd1")        
        result = self.ex.run()
        self.assertEqual(result, 0)


class TestDesign(unittest.TestCase):
    def setUp(self):
        #self.design = "examples/apps/euclid_d.c"
        self.design = getDesign()

        self.top = os.getcwd()
        self.llv = self.top + "/build/llv-obj/Debug/bin"
        
        dir = getDesignDir()
        self.options = getOptions()
        
        self.et = ExecTCC(self.top, self.design, self.llv, dir)
        
        libs = [("quixilica","%s/lib/quixilica" % self.top, \
                 "/home/rasr/pkgs/quixilica/vhdl"),
                ("trident", "%s/lib/trident" % self.top, \
                 "%s/lib/trident" % self.top)]
        self.ev = ExecVCOM(self.top, self.design, dir, libs)
        
        self.es = ExecVSIM(self.top, self.design, dir, libs)

        self.ex = ExecSetupXD1(self.top, self.design, dir, libs)


    def shortDescription(self):
        return getDesign().split('.')[0]+" "+str(self)

    def id(self):
        return getDesign().split('.')[0]+"/"+unittest.TestCase.id(self)

    def testTCC(self):
        options = "-t vhdl %s " % self.options
        self.et.setOption("tcc", options)
        self.et.dir = os.path.join(self.et.dir, "tcc")
        result = self.et.run()
        self.assertEqual(result, 0)

    def testVCOM(self):
        options = "-t vhdl %s " % self.options
        self.et.setOption("tcc", options)
        self.et.dir = os.path.join( self.et.dir, "vcom")        
        result = self.et.run()
        self.assertEqual(result, 0)
        
        self.ev.setLog(self.ev.design_noc+".vhd")
        self.ev.dir = os.path.join(self.ev.dir, "vcom")
        result = self.ev.run()
        self.assertEqual(result, 0)

    def testVSIM(self):
        options = "-t vhdl %s " % self.options
        self.et.setOption("tcc", options)
        self.et.dir = os.path.join(self.et.dir, "vsim")        
        result = self.et.run()
        self.assertEqual(result, 0)
        
        self.ev.setLog(self.ev.design_noc+".vhd")
        self.ev.dir = os.path.join(self.ev.dir, "vsim")                
        result = self.ev.run()
        self.assertEqual(result, 0)

        self.es.dir = os.path.join(self.es.dir, "vsim")        
        result = self.es.run()
        self.assertEqual(result, 0)

    def testTCCXD1(self):
        options = "-t vhdl --top %s " % self.options
        self.et.setOption("tcc", options)
        self.et.dir = os.path.join(self.et.dir,"tccxd1")        
        result = self.et.run()
        self.assertEqual(result, 0)

    def testVSIMXD1(self):
        options = "-t vhdl --top %s " % self.options
        self.et.setOption("tcc", options)
        self.et.dir = os.path.join(self.et.dir,"simxd1")        
        result = self.et.run()
        self.assertEqual(result, 0)
        
        self.ex.setLog(self.ev.design_noc+".xd1.vhd")
        self.ex.dir = os.path.join(self.ev.dir,"simxd1")        
        result = self.ex.run()
        self.assertEqual(result, 0)

        # then we need to run the tests with make


def parseCmdLine():
    import sys, getopt

    def usage():
        print 'Usage: apptest.py'
        print '  -s \t--short \t\tRun the short test instead of the default long test.'
        print '  -p \t--par  \t\t\tRun the place and route test. WARNING*Very Long*'
        print '  -g \t--grep=STRING \t\tGreps for tests with STRING in the tile.'
        print '  -o \t--options=OPTIONS \tSpecify tcc_options.'
        print '  -c \t--count=NUMBER \t\tOnly run the first n tests.'
        print '  -x \t--xml \t\t\tOutput a xml report.'
        print '  -t \t--timeout=SECONDS \tSet a timeout. Specifying 0 will disable it.'
        print '\t\t\t\t(The default timeout is %d seconds)' % timeout
        print '  -h \t--help \t\t\tDisplays this message.'

    test_design = TestDesign
    grep_string = None
    design_count = -1
    test_runner = "text"
    timeout = 120
    options = ""
    
    try:
        opts, args = getopt.getopt(sys.argv[1:], 'sg:pc:xo:t:h?', \
                                   ['short','help','design_count=', \
                                    'xml','options=','grep=','par','timeout='])
    except getopt.error, msg:
        print 'ERROR: %s' % msg
        print ''
        usage()
        sys.exit(2)
    for o,a in opts:
        if o in('-s','--short') : test_design = TestDesignShort
        if o in('-g','--grep') : grep_string = a
        if o in('-o','--options') : options = a
        if o in('-p','--par') : test_design = TestDesignPar
        if o in('-c','--count') : design_count = int(a)
        if o in('-x','--xml') : test_runner = "xml"
        if o in('-t','--timeout') : timeout = int(a)
        if o in('-h', '-?', '--help'):
            usage()
            sys.exit(0)

    # no real reason to send this back.
    setOptions(options)

    return test_design, grep_string, design_count, test_runner, timeout



def main():

    test_design, grep, count, runner, timeout = parseCmdLine()


    suite=unittest.TestSuite()
    setTimeout(timeout)
    initDesign(grep)
    initTestbench(grep)
    if (count > 0):
        design_count = count
    else:
        design_count = len(DESIGN_LIST)
    if (design_count < 1):
        raise Exception('No designs selected')

    suite.addTest(unittest.makeSuite(TestAnt))
    design_count = design_count - 1
    suite.addTest(unittest.makeSuite(test_design))
    for i in range(design_count):
        suite.addTest(unittest.makeSuite(TestInput))
        suite.addTest(unittest.makeSuite(test_design))

    #results = TextTestRunner(verbosity=2).run(suite)

    dir = "build/reports"
    if not os.path.exists(dir):
        os.makedirs(dir)
        
    time_str = "-"+time.strftime("%Y%m%d%H%M%S",time.localtime())
    if runner == "xml":
        results = XmlTextTestRunner(verbosity=2, output_dir=dir, append_name=time_str).run(suite)
    elif runner == "text":
        results = TextTestRunner(verbosity=2).run(suite)
    else:
       raise Exception('Unknown test runner.') 
    


if __name__ == '__main__':
    main()

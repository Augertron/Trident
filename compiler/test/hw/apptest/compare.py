#! /bin/env python

import xml.parsers.expat
import sys

class Suite:
    def __init__(self, errors, failures, tests, name, time):
        self.error = errors
        self.fail = failures
        self.tests = tests
        self.name = name
        self.time = time
        self.cases = {}

    def addCase(self,case):
        self.cases[case.name] = case

    def __str__(self):
        result = "Suite %s Total %s Error %s Fail %s\n" % (self.name,
                                                           self.tests,
                                        self.error, self.fail)
        for n in self.cases.keys():
            result = result + "\t"+str(self.cases[n])+"\n"
        
        return result


class Case:
    def __init__(self, name, time):
        self.name = name
        self.time = time
        self.result = "Pass"

    def setResult(self, result):
        self.result = result

    def __str__(self):
        return ("Case %s %s" % (self.name, self.result))        


class Failure:
    def __init__(self, name):
        self.name = name

    def __str__(self):
        return str(self.name)

class Error:
    def __init__(self,name):
        self.name = name

    def __str__(self):
        return str(self.name)        


class ReportParser:
    def __init__(self):
        self.suite = None
        self.s = []


    def parse(self, filename):
        self.p = xml.parsers.expat.ParserCreate()
        
        self.p.StartElementHandler = self.startElement
        self.p.EndElementHandler = self.endElement
        self.p.CharacterDataHandler = self.charData


        try:
            file = open(filename, "r")
        except IOError, msg:
            print file, ":", msg
            print "Cannot open file ",file
            return None
        
        try:
            self.p.Parse(file.read())
        except xml.parsers.expat.ExpatError:
            print "cannot parse ",filename
            file.close()
            return None
        file.close()

        return self.suite

    def startElement(self, name, attrs):
        #print 'Start element:', name, attrs

        if name == "testsuite":
            err = attrs['errors']
            fail = attrs['failures']
            myname = attrs['name']
            tests = attrs['tests']
            time = attrs['time']
        
            s = Suite(err, fail, tests, myname, time)
            self.s = []
            self.s.append(s)

        if name == "testcase":
            testname = attrs['name']
            testtime = attrs['time']

            c = Case(testname, testtime)
            self.s.append(c)

        if name == "error":
            errname = attrs['type']
            errname = errname.split('.')[1]
            self.s[-1].setResult(Error(errname))

        if name == "failure":
            errname = attrs['type']
            errname = errname.split('.')[1]
            self.s[-1].setResult(Failure(errname))
    
    
    def endElement(self, name):
        if name == "testsuite":
            self.suite = self.s.pop()

        if name == "testcase":
            case = self.s.pop()
            self.s[-1].addCase(case)
         
        
    def charData(self, data):
        #print 'Character data:', repr(data)
        pass

def parseCmdLine():
    import sys, getopt

    reports = []
    output = "output.csv"
    
    try:
        opts, args = getopt.getopt(sys.argv[1:], 'r:o:')
    except getopt.error, msg:
        print msg
        print ''
        print 'usage: compare.py'
        print '\t-r [filename] : report input (may be repeated)'
        print '\t-o [filename] : output file'
        sys.exit(2)
    for o,a in opts:
        if o == '-r' : reports.append(a)
        if o == '-o' : output = a

    return reports, output



def main():
    reports, output = parseCmdLine()
    
    rp = ReportParser()
    suites = []
    for r in reports:
        suite = rp.parse(r)
        if suite is not None:
            suites.append(suite)

    if len(reports) <= 0:
        print "No report input"
        sys.exit(-1)

    # then I need to compare multiple suites in some way.
    names = []
    for s in suites:
        sublist = s.cases.keys()
        for i in sublist:
            if names.count(i) <= 0:
                names.append(i)

    names.sort()

    try:
        file = open(output, "w")
    except IOError, msg:
        print file, ":", msg
        sys.exit(-1)


    file.write("Test,")
    for s in suites:
        file.write("%s," %(s.name))

    file.write("\n")

    for n in names:
        file.write("%s, " % (n))
        for s in suites:
            if s.cases.has_key(n):
                case = s.cases[n]
            else:
                case = None

            if case is None:
                file.write("None,")
            else:
                file.write("%s, " %(case.result))
        file.write("\n")
    file.write("\n")
    file.write("Totals\n")
    
    file.write("Tests,")
    for s in suites:
        file.write("%s," %(s.tests))
    file.write("\n")

    file.write("Pass,")
    for s in suites:
        success = int(s.tests) - int(s.error) - int(s.fail)
        file.write("%s," %(success))
    file.write("\n")        

    file.write("Fail,")
    for s in suites:
        success = int(s.error) + int(s.fail)
        file.write("%s," %(success))
    file.write("\n")


if __name__ == '__main__':
    main()

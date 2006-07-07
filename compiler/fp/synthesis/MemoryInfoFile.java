/*
 *
 @LICENSE@
 */


package fp.synthesis;

import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

public class MemoryInfoFile implements Text {
  
  Writer _writer;
  RegInfoList _regs;
  MemInfoList _mem;

  public MemoryInfoFile(String fileName, RegInfoList regInfo, 
		        MemInfoList memInfo) {
    try {
      _writer = new FileWriter(fileName);
      _regs = regInfo;
      _mem = memInfo;
      
      writeFile(_writer);
      _writer.close();
    } catch (IOException e) {
      throw new SynthesisException("Exception occured while trying to write"+
				   "to file: "+e.getMessage());
    }
  }

  public void writeFile(Writer writer) {
    try {
      String text = toText("");
      writer.write(text,0,text.length());
    } catch (IOException e) {
      throw new SynthesisException("Exception occured while trying to write"+
				   "to file: "+e.getMessage());
    }
  }

  public String toText(String prefix) {
      
    // write regs
    // write arrays
    // anything else ?

    StringBuffer sbuf = new StringBuffer();
    sbuf.append(prefix).append(START);
    sbuf.append("Config").append(SPACE);
    sbuf.append(LINE);
    
    sbuf.append(prefix).append(TAB);
    sbuf.append(START);
    sbuf.append("target").append(SPACE);
    sbuf.append("cray_xd1_v12");
    sbuf.append(END).append(LINE);


    sbuf.append(prefix).append(TAB);
    sbuf.append(START);
    sbuf.append("design").append(SPACE);
    sbuf.append("NAME_HERE");
    sbuf.append(END).append(LINE);

    if (_regs != null) {
      sbuf.append(_regs.toText(prefix+TAB));
    }

    if (_mem != null) {
      sbuf.append(_mem.toText(prefix+TAB));
    }

    sbuf.append(prefix).append(END).append(LINE);
    return sbuf.toString();
  }
  
}
    
      

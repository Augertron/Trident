/*
 *
 @LICENSE@
 */


package fp.passes;

class PassStat {

  long millis = 0;
  String name = "";
  
  PassStat(String n, long i) {
    name = n;
    millis = i;
  }

  public String toString(long total) {
    int result = (int)((millis*10000) / total);
    StringBuffer buf = millisPercent(result);

    StringBuffer sbuf = pad(name,30);

    return "  "+sbuf+"  "+buf+"%";
  }
   
  static StringBuffer millisPercent(int result) {
    int result_h = result / 100;
    int result_f = result % 100;
    StringBuffer buf = new StringBuffer();
    if (result_h < 10) buf.append("0");
    buf.append(result_h).append(".");
    if (result_f < 10) buf.append("0");
    buf.append(result_f);
    return buf;
  }

  static StringBuffer pad(String input, int size) {
    StringBuffer result = new StringBuffer(input);
    while(result.length() < size) {
      result.append(" ");
    }
    return result;
  }


}


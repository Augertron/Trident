--LA-CC 05-135 Trident @VERSION@
--
--Copyright Notice
--Copyright @YEAR@ (c) the Regents of the University of California.
--
--This Software was produced under a U.S. Government contract 
--(W-7405-ENG-36) by Los Alamos National Laboratory, which is operated by 
--the University of California for the U.S. Department of Energy. The U.S. 
--Government is licensed to use, reproduce, and distribute this Software. 
--Permission is granted to the public to copy and use this Software without 
--charge, provided that this Notice and any statement of authorship are 
--reproduced on all copies. Neither the Government nor the University makes 
--any warranty, express or implied, or assumes any liability or 
--responsibility for the user of this Software.


library ieee;

use ieee.std_logic_unsigned.all;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

entity mux4 is
  port(output: out std_logic;
       s0: in std_logic;
       s1: in std_logic;
       in0: in std_logic;
       in1: in std_logic;
       in2: in std_logic;
       in3: in std_logic);
end entity mux4;

architecture mux4_arch of mux4 is
begin
  output <= in0 when s1 = '0' and s0 = '0' else
            in1 when s1 = '0' and s0 = '1' else
            in2 when s1 = '1' and s0 = '0' else
            in3;
end architecture mux4_arch; 


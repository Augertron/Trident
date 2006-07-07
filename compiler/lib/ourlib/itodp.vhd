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


-- this file was written by me by hand



 
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

library ieee;

use ieee.std_logic_unsigned.all;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

entity mux2 is
  port(output: out std_logic;
       s0: in std_logic;
       in0: in std_logic;
       in1: in std_logic);
end entity mux2;

architecture mux2_arch of mux2 is
begin
  output <= in0 when s0 = '0' else
            in1;
end architecture mux2_arch; 

library ieee;

use ieee.std_logic_unsigned.all;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

entity mux64 is
  generic (constant pinmap : in integer);
  port(output: out std_logic;
       clk: in std_logic;
       sel: in std_logic_vector(5 downto 0);
       inputs: in std_logic_vector(63 downto 0));
end entity mux64;

architecture mux64_arch of mux64 is
    component mux4 is
      port(output: out std_logic;
	   s0: in std_logic;
	   s1: in std_logic;
	   in0: in std_logic;
	   in1: in std_logic;
	   in2: in std_logic;
	   in3: in std_logic);
    end component mux4;
    signal inputbuffer: std_logic_vector(63 downto 0);
    signal mux4_0_out: std_logic_vector(0 to 15);
    signal mux4_0_out_reg: std_logic_vector(0 to 15);
    signal mux4_1_out: std_logic_vector(0 to 3);
    signal mux4_1_out_reg: std_logic_vector(0 to 3);
    signal sel_0: std_logic_vector(5 downto 0);
    signal sel_0_0: std_logic_vector(5 downto 0);
    signal sel_0_1: std_logic_vector(5 downto 0);
    signal sel_0_2: std_logic_vector(5 downto 0);
    signal sel_0_3: std_logic_vector(5 downto 0);
    signal sel_0_4: std_logic_vector(5 downto 0);
    signal sel_0_5: std_logic_vector(5 downto 0);
    signal sel_0_6: std_logic_vector(5 downto 0);
    signal sel_0_7: std_logic_vector(5 downto 0);
    signal sel_1: std_logic_vector(5 downto 0);
    signal sel_2: std_logic_vector(5 downto 0);
    constant pin0 : integer := 63-pinmap;
begin
  input_reg: process (clk)
  begin
    if (clk'event and clk = '1')  then
    inputbuffer(63 downto pin0) <= inputs(pinmap downto 0);
    inputbuffer(pin0 - 1 downto 0) <= (others => '0');
    sel_0 <= sel;
    sel_0_0 <= sel;
    sel_0_1 <= sel;
    sel_0_2 <= sel;
    sel_0_3 <= sel;
    sel_0_4 <= sel;
    sel_0_5 <= sel;
    sel_0_6 <= sel;
    sel_0_7 <= sel;
    end if;
  end process input_reg;


  mux4_0 : component mux4
    port map(
      output => mux4_0_out(0),
      s0 => sel_0_0(0),
      s1 => sel_0_0(1),
      in0 => inputbuffer(63),
      in1 => inputbuffer(62),
      in2 => inputbuffer(61),
      in3 => inputbuffer(60)
    );
  mux4_1 : component mux4
    port map(
      output => mux4_0_out(1),
      s0 => sel_0_0(0),
      s1 => sel_0_0(1),
      in0 => inputbuffer(59),
      in1 => inputbuffer(58),
      in2 => inputbuffer(57),
      in3 => inputbuffer(56)
    );
  mux4_2 : component mux4
    port map(
      output => mux4_0_out(2),
      s0 => sel_0_1(0),
      s1 => sel_0_1(1),
      in0 => inputbuffer(55),
      in1 => inputbuffer(54),
      in2 => inputbuffer(53),
      in3 => inputbuffer(52)
    );
  mux4_3 : component mux4
    port map(
      output => mux4_0_out(3),
      s0 => sel_0_1(0),
      s1 => sel_0_1(1),
      in0 => inputbuffer(51),
      in1 => inputbuffer(50),
      in2 => inputbuffer(49),
      in3 => inputbuffer(48)
    );
  mux4_4 : component mux4
    port map(
      output => mux4_0_out(4),
      s0 => sel_0_2(0),
      s1 => sel_0_2(1),
      in0 => inputbuffer(47),
      in1 => inputbuffer(46),
      in2 => inputbuffer(45),
      in3 => inputbuffer(44)
    );
  mux4_5 : component mux4
    port map(
      output => mux4_0_out(5),
      s0 => sel_0_2(0),
      s1 => sel_0_2(1),
      in0 => inputbuffer(43),
      in1 => inputbuffer(42),
      in2 => inputbuffer(41),
      in3 => inputbuffer(40)
    );
  mux4_6 : component mux4
    port map(
      output => mux4_0_out(6),
      s0 => sel_0_3(0),
      s1 => sel_0_3(1),
      in0 => inputbuffer(39),
      in1 => inputbuffer(38),
      in2 => inputbuffer(37),
      in3 => inputbuffer(36)
    );
  mux4_7 : component mux4
    port map(
      output => mux4_0_out(7),
      s0 => sel_0_3(0),
      s1 => sel_0_3(1),
      in0 => inputbuffer(35),
      in1 => inputbuffer(34),
      in2 => inputbuffer(33),
      in3 => inputbuffer(32)
    );
  mux4_8 : component mux4
    port map(
      output => mux4_0_out(8),
      s0 => sel_0_4(0),
      s1 => sel_0_4(1),
      in0 => inputbuffer(31),
      in1 => inputbuffer(30),
      in2 => inputbuffer(29),
      in3 => inputbuffer(28)
    );
  mux4_9 : component mux4
    port map(
      output => mux4_0_out(9),
      s0 => sel_0_4(0),
      s1 => sel_0_4(1),
      in0 => inputbuffer(27),
      in1 => inputbuffer(26),
      in2 => inputbuffer(25),
      in3 => inputbuffer(24)
    );
  mux4_10 : component mux4
    port map(
      output => mux4_0_out(10),
      s0 => sel_0_5(0),
      s1 => sel_0_5(1),
      in0 => inputbuffer(23),
      in1 => inputbuffer(22),
      in2 => inputbuffer(21),
      in3 => inputbuffer(20)
    );
  mux4_11 : component mux4
    port map(
      output => mux4_0_out(11),
      s0 => sel_0_5(0),
      s1 => sel_0_5(1),
      in0 => inputbuffer(19),
      in1 => inputbuffer(18),
      in2 => inputbuffer(17),
      in3 => inputbuffer(16)
    );
  mux4_12 : component mux4
    port map(
      output => mux4_0_out(12),
      s0 => sel_0_6(0),
      s1 => sel_0_6(1),
      in0 => inputbuffer(15),
      in1 => inputbuffer(14),
      in2 => inputbuffer(13),
      in3 => inputbuffer(12)
    );
  mux4_13 : component mux4
    port map(
      output => mux4_0_out(13),
      s0 => sel_0_6(0),
      s1 => sel_0_6(1),
      in0 => inputbuffer(11),
      in1 => inputbuffer(10),
      in2 => inputbuffer(9),
      in3 => inputbuffer(8)
    );
  mux4_14 : component mux4
    port map(
      output => mux4_0_out(14),
      s0 => sel_0_7(0),
      s1 => sel_0_7(1),
      in0 => inputbuffer(7),
      in1 => inputbuffer(6),
      in2 => inputbuffer(5),
      in3 => inputbuffer(4)
    );
  mux4_15 : component mux4
    port map(
      output => mux4_0_out(15),
      s0 => sel_0_7(0),
      s1 => sel_0_7(1),
      in0 => inputbuffer(3),
      in1 => inputbuffer(2),
      in2 => inputbuffer(1),
      in3 => inputbuffer(0)
    );

  middle_reg: process (clk)
  begin
    if (clk'event and clk = '1')  then
      mux4_0_out_reg <= mux4_0_out;
      sel_1 <= sel_0;
    end if;
  end process middle_reg;

  mux4_16 : component mux4
    port map(
      output => mux4_1_out(0),
      s0 => sel_1(2),
      s1 => sel_1(3),
      in0 => mux4_0_out_reg(0),
      in1 => mux4_0_out_reg(1),
      in2 => mux4_0_out_reg(2),
      in3 => mux4_0_out_reg(3)
    );
  mux4_17 : component mux4
    port map(
      output => mux4_1_out(1),
      s0 => sel_1(2),
      s1 => sel_1(3),
      in0 => mux4_0_out_reg(4),
      in1 => mux4_0_out_reg(5),
      in2 => mux4_0_out_reg(6),
      in3 => mux4_0_out_reg(7)
    );
  mux4_18 : component mux4
    port map(
      output => mux4_1_out(2),
      s0 => sel_1(2),
      s1 => sel_1(3),
      in0 => mux4_0_out_reg(8),
      in1 => mux4_0_out_reg(9),
      in2 => mux4_0_out_reg(10),
      in3 => mux4_0_out_reg(11)
    );
  mux4_19 : component mux4
    port map(
      output => mux4_1_out(3),
      s0 => sel_1(2),
      s1 => sel_1(3),
      in0 => mux4_0_out_reg(12),
      in1 => mux4_0_out_reg(13),
      in2 => mux4_0_out_reg(14),
      in3 => mux4_0_out_reg(15)
    );

  last_reg: process (clk)
  begin
    if (clk'event and clk = '1')  then
      mux4_1_out_reg <= mux4_1_out;
      sel_2 <= sel_1;
    end if;
  end process last_reg;


  mux4_20 : component mux4
    port map(
      output => output,
      s0 => sel_2(4),
      s1 => sel_2(5),
      in0 => mux4_1_out_reg(0),
      in1 => mux4_1_out_reg(1),
      in2 => mux4_1_out_reg(2),
      in3 => mux4_1_out_reg(3)
    );
  
end architecture mux64_arch;

library ieee;

use ieee.std_logic_unsigned.all;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

entity log_shift_left is
  port(data_in: in std_logic_vector(63 downto 0);
       shift_cnt: in std_logic_vector(5 downto 0);
       data_out: out std_logic_vector(63 downto 0);
       clk: in std_logic);
end entity log_shift_left;

architecture log_shift_left_arch of log_shift_left is
   component mux64 is
     generic (constant pinmap : in integer);
     port(output: out std_logic;
          clk: in std_logic;
          sel: in std_logic_vector(5 downto 0);
          inputs: in std_logic_vector(63 downto 0));
   end component mux64;
   signal out_buffer: std_logic_vector(63 downto 0);
   signal input_buffer: std_logic_vector(63 downto 0);
   signal input_buffer_0: std_logic_vector(63 downto 0);
   signal input_buffer_0_wire: std_logic_vector(63 downto 0);
   signal input_buffer_1: std_logic_vector(63 downto 0);
   signal input_buffer_1_wire: std_logic_vector(63 downto 0);
   signal input_buffer_2: std_logic_vector(63 downto 0);
   signal input_buffer_2_wire: std_logic_vector(63 downto 0);
   signal input_buffer_3: std_logic_vector(63 downto 0);
   signal input_buffer_3_wire: std_logic_vector(63 downto 0);
   signal input_buffer_4: std_logic_vector(63 downto 0);
   signal input_buffer_4_wire: std_logic_vector(63 downto 0);
   signal input_buffer_5: std_logic_vector(63 downto 0);
   signal input_buffer_5_wire: std_logic_vector(63 downto 0);
   signal input_buffer_6: std_logic_vector(63 downto 0);
   signal input_buffer_6_wire: std_logic_vector(63 downto 0);
   signal input_buffer_7: std_logic_vector(63 downto 0);
   signal input_buffer_7_wire: std_logic_vector(63 downto 0);
   signal shift_cnt_reg: std_logic_vector(5 downto 0);
   signal shift_cnt_0: std_logic_vector(5 downto 0);
   signal shift_cnt_1: std_logic_vector(5 downto 0);
   signal shift_cnt_2: std_logic_vector(5 downto 0);
   signal shift_cnt_3: std_logic_vector(5 downto 0);
   signal shift_cnt_4: std_logic_vector(5 downto 0);
   signal shift_cnt_5: std_logic_vector(5 downto 0);
   signal shift_cnt_6: std_logic_vector(5 downto 0);
   signal shift_cnt_7: std_logic_vector(5 downto 0);
begin
  divide_data: process (clk) is 
  begin
      if  (clk'event and clk = '1')  then
	input_buffer <= data_in;
	shift_cnt_reg <= shift_cnt;
      end if;
  end process divide_data;
  input_buffer_0_wire <= input_buffer;
  input_buffer_1_wire <= input_buffer;
  input_buffer_2_wire <= input_buffer;
  input_buffer_3_wire <= input_buffer;
  input_buffer_4_wire <= input_buffer;
  input_buffer_5_wire <= input_buffer;
  input_buffer_6_wire <= input_buffer;
  input_buffer_7_wire <= input_buffer;
  divide_data_2: process (clk) is 
  begin
      if  (clk'event and clk = '1')  then
	input_buffer_0 <= input_buffer_0_wire;
	input_buffer_1 <= input_buffer_1_wire;
	input_buffer_2 <= input_buffer_2_wire;
	input_buffer_3 <= input_buffer_3_wire;
	input_buffer_4 <= input_buffer_4_wire;
	input_buffer_5 <= input_buffer_5_wire;
	input_buffer_6 <= input_buffer_6_wire;
	input_buffer_7 <= input_buffer_7_wire;
	shift_cnt_0 <= shift_cnt_reg;
	shift_cnt_1 <= shift_cnt_reg;
	shift_cnt_2 <= shift_cnt_reg;
	shift_cnt_3 <= shift_cnt_reg;
	shift_cnt_4 <= shift_cnt_reg;
	shift_cnt_5 <= shift_cnt_reg;
	shift_cnt_6 <= shift_cnt_reg;
	shift_cnt_7 <= shift_cnt_reg;
      end if;
  end process divide_data_2;
  --foreachpin: for pin0 in 0 to data_in'high generate
  --begin
  --  mux64_shift: component mux64
  --  generic map (pinmap => pin0)
  --  port map ( output => out_buffer(pin0),
  --             clk => clk,
  --  	       inputs(63 downto 0) => 
  --  		    data_in(63 downto 0),
  --  	       sel => shift_cnt);
  --end generate foreachpin;
  
  foreachpin_0: for pin0 in 0 to 7 generate
  begin
	mux64_shift: component mux64
	  generic map (pinmap => pin0)
	  port map ( output => out_buffer(pin0),
	             clk => clk,
		     inputs(63 downto 0) => 
		     	  input_buffer_0(63 downto 0),
	             sel => shift_cnt_0);
  end generate foreachpin_0;
  foreachpin_1: for pin0 in 8 to 15 generate
  begin
	mux64_shift: component mux64
	  generic map (pinmap => pin0)
	  port map ( output => out_buffer(pin0),
	             clk => clk,
		     inputs(63 downto 0) => 
		     	  input_buffer_1(63 downto 0),
	             sel => shift_cnt_1);
  end generate foreachpin_1;
  foreachpin_2: for pin0 in 16 to 23 generate
  begin
	mux64_shift: component mux64
	  generic map (pinmap => pin0)
	  port map ( output => out_buffer(pin0),
	             clk => clk,
		     inputs(63 downto 0) => 
		     	  input_buffer_2(63 downto 0),
	             sel => shift_cnt_2);
  end generate foreachpin_2;
  foreachpin_3: for pin0 in 24 to 31 generate
  begin
	mux64_shift: component mux64
	  generic map (pinmap => pin0)
	  port map ( output => out_buffer(pin0),
	             clk => clk,
		     inputs(63 downto 0) => 
		     	  input_buffer_3(63 downto 0),
	             sel => shift_cnt_3);
  end generate foreachpin_3;
  foreachpin_4: for pin0 in 32 to 39 generate
  begin
	mux64_shift: component mux64
	  generic map (pinmap => pin0)
	  port map ( output => out_buffer(pin0),
	             clk => clk,
		     inputs(63 downto 0) => 
		     	  input_buffer_4(63 downto 0),
	             sel => shift_cnt_4);
  end generate foreachpin_4;
  foreachpin_5: for pin0 in 40 to 47 generate
  begin
	mux64_shift: component mux64
	  generic map (pinmap => pin0)
	  port map ( output => out_buffer(pin0),
	             clk => clk,
		     inputs(63 downto 0) => 
		     	  input_buffer_5(63 downto 0),
	             sel => shift_cnt_5);
  end generate foreachpin_5;
  foreachpin_6: for pin0 in 48 to 55 generate
  begin
	mux64_shift: component mux64
	  generic map (pinmap => pin0)
	  port map ( output => out_buffer(pin0),
	             clk => clk,
		     inputs(63 downto 0) => 
		     	  input_buffer_6(63 downto 0),
	             sel => shift_cnt_6);
  end generate foreachpin_6;
  foreachpin_7: for pin0 in 56 to 63 generate
  begin
	mux64_shift: component mux64
	  generic map (pinmap => pin0)
	  port map ( output => out_buffer(pin0),
	             clk => clk,
		     inputs(63 downto 0) => 
		     	  input_buffer_7(63 downto 0),
	             sel => shift_cnt_7);
  end generate foreachpin_7;
  
  
  store_data: process (clk) is 
  begin
      if  (clk'event and clk = '1')  then
        data_out <= out_buffer;
      end if;
  end process store_data;
  
end architecture log_shift_left_arch;

library ieee;

use ieee.std_logic_unsigned.all;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

entity i_to_dp is
  port(vec_int: in std_logic_vector(63 downto 0);
       vec_dp:out std_logic_vector(63 downto 0);
       clk: in std_logic);
end entity i_to_dp;

architecture behavioural of i_to_dp is

--======================================================--
--                     FUNCTIONS                        --
--======================================================--
	--function 
	function break_apart_3_lg (input: std_logic_vector) 
	              return std_logic is
		variable ans : std_logic; 
	begin
		ans := input(3);
		return ans; 
	end function break_apart_3_lg;
	--function 
	function break_apart_2_lg (input: std_logic_vector) 
	              return std_logic is
		variable ans : std_logic; 
	begin
		ans := input(2);
		return ans; 
	end function break_apart_2_lg;
	--function 
	function break_apart_1_lg (input: std_logic_vector) 
	              return std_logic is
		variable ans : std_logic; 
	begin
		ans := input(1);
		return ans; 
	end function break_apart_1_lg;
	--function 
	function break_apart_0_lg (input: std_logic_vector) 
	              return std_logic is
		variable ans : std_logic; 
	begin
		ans := input(0);
		return ans; 
	end function break_apart_0_lg;
	--function 
	function break_apart_3 (input: std_logic_vector) 
	              return std_logic_vector is
		variable ans : std_logic_vector(input'high/4 downto 0); 
		variable cntr : integer := input'high/4;
	begin
		break_up: for i in input'high downto 3*input'high/4+1 loop
		    ans(cntr) := input(i);
		    cntr := cntr - 1;
		end loop break_up;
		return ans; 
	end function break_apart_3;
	--function 
	function break_apart_2 (input: std_logic_vector) 
	              return std_logic_vector is
		variable ans : std_logic_vector(input'high/4 downto 0); 
		variable cntr : integer := input'high/4;
	begin
		break_up: for i in 3*input'high/4 downto input'high/2+1 loop
		    ans(cntr) := input(i);
		    cntr := cntr - 1;
		end loop break_up;
		return ans; 
	end function break_apart_2;
	--function 
	function break_apart_1 (input: std_logic_vector) 
	              return std_logic_vector is
		variable ans : std_logic_vector(input'high/4 downto 0); 
		variable cntr : integer := input'high/4;
	begin
		break_up: for i in input'high/2 downto input'high/4+1 loop
		    ans(cntr) := input(i);
		    cntr := cntr - 1;
		end loop break_up;
		return ans; 
	end function break_apart_1;
	--function 
	function break_apart_0 (input: std_logic_vector) 
	              return std_logic_vector is
		variable ans : std_logic_vector(input'high/4 downto 0); 
		variable cntr : integer := input'high/4;
	begin
		break_up: for i in input'high/4 downto 0 loop
		    ans(cntr) := input(i);
		    cntr := cntr - 1;
		end loop break_up;
		return ans; 
	end function break_apart_0;

    component log_shift_left is
      port(data_in: in std_logic_vector(63 downto 0);
           shift_cnt: in std_logic_vector(5 downto 0);
           data_out: out std_logic_vector(63 downto 0);
	   clk: in std_logic);
    end component log_shift_left;
    
    
    signal mant: std_logic_vector(51 downto 0);
    signal sine: std_logic;
    signal sine_0: std_logic;
    signal sine_1: std_logic;
    signal sine_1_0: std_logic;
    signal sine_1_1: std_logic;
    signal sine_2: std_logic;
    signal sine_2_0: std_logic;
    signal sine_3: std_logic;
    --signal sine_4: std_logic;
    --signal sine_5: std_logic;
    --signal sine_6: std_logic;
    signal sine_7: std_logic;
    signal sine_8: std_logic;
    signal sine_9: std_logic;
    signal sine_10: std_logic;
    signal sine_11: std_logic;
    signal sine_12: std_logic;
    signal temp_buffer: std_logic_vector(63 downto 0);
    signal temp_buffer_0: std_logic_vector(63 downto 0);
    signal temp_buffer_1: std_logic_vector(63 downto 0);
    signal temp_buffer_1_0: std_logic_vector(63 downto 0);
    signal temp_buffer_1_1: std_logic_vector(63 downto 0);
    signal temp_buffer_2: std_logic_vector(63 downto 0);
    signal temp_buffer_2_0: std_logic_vector(63 downto 0);
    signal temp_buffer_3: std_logic_vector(63 downto 0);
    --signal temp_buffer_4: std_logic_vector(63 downto 0);
    --signal temp_buffer_5: std_logic_vector(63 downto 0);
    --signal temp_buffer_6: std_logic_vector(63 downto 0);
    signal temp_buffer_7: std_logic_vector(63 downto 0);
    signal temp_buffer_8: std_logic_vector(63 downto 0);
    signal temp_buffer_9: std_logic_vector(63 downto 0);
    signal temp_buffer_10: std_logic_vector(63 downto 0);
    signal temp_buffer_11: std_logic_vector(63 downto 0);
    signal temp_buffer_12: std_logic_vector(63 downto 0);
    --signal temp_int_5_t: std_logic_vector(31 downto 0);
    --signal temp_int_5_b: std_logic_vector(31 downto 0);
    signal temp_int_4_3: std_logic_vector(15 downto 0);
    signal temp_int_4_3_reg: std_logic_vector(15 downto 0);
    signal temp_int_4_3_reg_0: std_logic_vector(15 downto 0);
    signal temp_int_4_3_cond: std_logic;
    signal temp_int_4_3_cond_reg: std_logic;
    signal temp_int_4_3_cond_0: std_logic;
    signal temp_int_4_3_cond_1: std_logic;
    signal temp_int_4_3_cond_2: std_logic;
    signal temp_int_4_3_cond_3: std_logic;
    signal temp_int_4_3_cond_4: std_logic;
    signal temp_int_4_3_cond_5: std_logic;
    signal temp_int_4_3_cond_reg_0: std_logic;
    signal temp_int_4_3_cond_reg_1: std_logic;
    signal temp_int_4_3_cond_reg_2: std_logic;
    signal temp_int_4_3_cond_reg_3: std_logic;
    signal temp_int_4_3_cond_reg_4: std_logic;
    signal temp_int_4_3_cond_reg_5: std_logic;
    signal temp_int_4_2: std_logic_vector(15 downto 0);
    signal temp_int_4_2_reg: std_logic_vector(15 downto 0);
    signal temp_int_4_2_reg_0: std_logic_vector(15 downto 0);
    signal temp_int_4_2_cond: std_logic;
    signal temp_int_4_2_cond_reg: std_logic;
    signal temp_int_4_2_cond_0: std_logic;
    signal temp_int_4_2_cond_1: std_logic;
    signal temp_int_4_2_cond_2: std_logic;
    signal temp_int_4_2_cond_3: std_logic;
    signal temp_int_4_2_cond_4: std_logic;
    signal temp_int_4_2_cond_5: std_logic;
    signal temp_int_4_2_cond_reg_0: std_logic;
    signal temp_int_4_2_cond_reg_1: std_logic;
    signal temp_int_4_2_cond_reg_2: std_logic;
    signal temp_int_4_2_cond_reg_3: std_logic;
    signal temp_int_4_2_cond_reg_4: std_logic;
    signal temp_int_4_2_cond_reg_5: std_logic;
    signal temp_int_4_1: std_logic_vector(15 downto 0);
    signal temp_int_4_1_reg: std_logic_vector(15 downto 0);
    signal temp_int_4_1_reg_0: std_logic_vector(15 downto 0);
    signal temp_int_4_1_cond: std_logic;
    signal temp_int_4_1_cond_reg: std_logic;
    signal temp_int_4_1_cond_0: std_logic;
    signal temp_int_4_1_cond_1: std_logic;
    signal temp_int_4_1_cond_2: std_logic;
    signal temp_int_4_1_cond_3: std_logic;
    signal temp_int_4_1_cond_4: std_logic;
    signal temp_int_4_1_cond_5: std_logic;
    signal temp_int_4_1_cond_reg_0: std_logic;
    signal temp_int_4_1_cond_reg_1: std_logic;
    signal temp_int_4_1_cond_reg_2: std_logic;
    signal temp_int_4_1_cond_reg_3: std_logic;
    signal temp_int_4_1_cond_reg_4: std_logic;
    signal temp_int_4_1_cond_reg_5: std_logic;
    signal temp_int_4_0: std_logic_vector(15 downto 0);
    signal temp_int_4_0_reg: std_logic_vector(15 downto 0);
    signal temp_int_4_0_reg_0: std_logic_vector(15 downto 0);
    --signal temp_int_3_t: std_logic_vector(7 downto 0);
    --signal temp_int_3_b: std_logic_vector(7 downto 0);
    signal temp_int_2_3: std_logic_vector(3 downto 0);
    signal temp_int_2_3_wire: std_logic_vector(3 downto 0);
    signal temp_int_2_3_reg: std_logic_vector(3 downto 0);
    signal temp_int_2_3_cond: std_logic;
    signal temp_int_2_3_cond_reg: std_logic;
    signal temp_int_2_2: std_logic_vector(3 downto 0);
    signal temp_int_2_2_wire: std_logic_vector(3 downto 0);
    signal temp_int_2_2_reg: std_logic_vector(3 downto 0);
    signal temp_int_2_2_cond: std_logic;
    signal temp_int_2_2_cond_reg: std_logic;
    signal temp_int_2_1: std_logic_vector(3 downto 0);
    signal temp_int_2_1_wire: std_logic_vector(3 downto 0);
    signal temp_int_2_1_reg: std_logic_vector(3 downto 0);
    signal temp_int_2_1_cond: std_logic;
    signal temp_int_2_1_cond_reg: std_logic;
    signal temp_int_2_0: std_logic_vector(3 downto 0);
    signal temp_int_2_0_wire: std_logic_vector(3 downto 0);
    signal temp_int_2_0_reg: std_logic_vector(3 downto 0);
    --signal temp_int_1_t: std_logic_vector(1 downto 0);
    --signal temp_int_1_b: std_logic_vector(1 downto 0);
    signal temp_int_0_3: std_logic;
    signal temp_int_0_3_wire: std_logic;
    signal temp_int_0_2: std_logic;
    signal temp_int_0_2_wire: std_logic;
    signal temp_int_0_1: std_logic;
    signal temp_int_0_1_wire: std_logic;
    signal temp_int_0_0: std_logic;
    signal temp_int_0_0_wire: std_logic;
    signal shift_cnt: std_logic_vector(5 downto 0);
    signal shift_cnt_wire: std_logic_vector(1 downto 0);
    signal shift_cnt_2: std_logic_vector(5 downto 0);
    signal shift_cnt_2_0: std_logic_vector(5 downto 0);
    signal shift_cnt_2_wire: std_logic_vector(1 downto 0);
    signal shift_cnt_3: std_logic_vector(5 downto 0);
    signal shift_cnt_3_wire: std_logic_vector(1 downto 0);
    --signal shift_cnt_4: std_logic_vector(5 downto 0);
    --signal shift_cnt_5: std_logic_vector(5 downto 0);
    --signal shift_cnt_6: std_logic_vector(5 downto 0);
    signal exp: std_logic_vector(10 downto 0);
    signal exp_wire: std_logic_vector(1 downto 0);
    signal exp_2: std_logic_vector(10 downto 0);
    signal exp_2_0: std_logic_vector(10 downto 0);
    signal exp_2_wire: std_logic_vector(1 downto 0);
    signal exp_3: std_logic_vector(10 downto 0);
    signal exp_3_wire: std_logic_vector(1 downto 0);
    --signal exp_4: std_logic_vector(10 downto 0);
    --signal exp_5: std_logic_vector(10 downto 0);
    --signal exp_6: std_logic_vector(10 downto 0);
    signal temp: std_logic_vector(10 downto 0);
    signal temp2: std_logic;
    signal exp_out: std_logic_vector(10 downto 0);
    signal exp_out_reg_0: std_logic_vector(10 downto 0);
    signal exp_out_reg_1: std_logic_vector(10 downto 0);
    signal exp_out_1: std_logic_vector(10 downto 0);
    signal exp_out_2: std_logic_vector(10 downto 0);
    signal exp_out_3: std_logic_vector(10 downto 0);
    signal exp_out_4: std_logic_vector(10 downto 0);
    signal exp_out_5: std_logic_vector(10 downto 0);
begin
  shftlftlog : component log_shift_left
    port map(
      data_in => temp_buffer_3, 
      data_out(63) => temp2,
      data_out(62 downto 11) => mant,
      data_out(10 downto 0) => temp,
      shift_cnt => shift_cnt_3,
      clk => clk
    );
  
  
  
  
  exp_add: process (clk)
  begin
    if (clk'event and clk = '1')  then
      --exp_out <= exp_3 + b"1111111111";
      --exp_out(10) <= exp_3(10) xor '1';
      --exp_out(9 downto 0) <= exp_3(9 downto 0);
      exp_out_reg_0 <= exp_3;
      exp_out_reg_1 <= exp_3;
      --exp_out(0) <= not exp_3(0);
      sine_7 <= sine_3;
      temp_buffer_7 <= temp_buffer_3;
    end if;
  end process exp_add;
  
  exp_out(10 downto 1) <= '1' & exp_out_reg_0(9 downto 1) 
                                 when exp_out_reg_1(0) = '1' else
			  '1' & exp_out_reg_0(9 downto 2) & not exp_out_reg_0(1)
			         when exp_out_reg_1(1) = '1' else
			  '1' & exp_out_reg_0(9 downto 3) & not exp_out_reg_0(2 downto 1)
			         when exp_out_reg_1(2) = '1' else
			  '1' & exp_out_reg_0(9 downto 4) & not exp_out_reg_0(3 downto 1)
			         when exp_out_reg_1(3) = '1' else
			  '1' & exp_out_reg_0(9 downto 5) & not exp_out_reg_0(4 downto 1)
			         when exp_out_reg_1(4) = '1' else
			  '1' & exp_out_reg_0(9 downto 6) & not exp_out_reg_0(5 downto 1)
			         when exp_out_reg_1(5) = '1' else
			  '0' & not exp_out_reg_0(9 downto 1);
  exp_out(0) <= not exp_out_reg_0(0);
  
  save_1: process (clk)
  begin
    if (clk'event and clk = '1')  then
      --exp_out_1 <= exp_out - '1';
      exp_out_1 <= exp_out;
      sine_8 <= sine_7;
      temp_buffer_8 <= temp_buffer_7;
    end if;
  end process save_1;
  
  save_2: process (clk)
  begin
    if (clk'event and clk = '1')  then
      exp_out_2 <= exp_out_1;
      sine_9 <= sine_8;
      temp_buffer_9 <= temp_buffer_8;
    end if;
  end process save_2;
  
  save_3: process (clk)
  begin
    if (clk'event and clk = '1')  then
      exp_out_3 <= exp_out_2;
      sine_10 <= sine_9;
      temp_buffer_10 <= temp_buffer_9;
    end if;
  end process save_3;
  
  save_4: process (clk)
  begin
    if (clk'event and clk = '1')  then
      exp_out_4 <= exp_out_3;
      sine_11 <= sine_10;
      temp_buffer_11 <= temp_buffer_10;
    end if;
  end process save_4;
  
  save_5: process (clk)
  begin
    if (clk'event and clk = '1')  then
      exp_out_5 <= exp_out_4;
      sine_12 <= sine_11;
      temp_buffer_12 <= temp_buffer_11;
    end if;
  end process save_5;
  
  --save_result: process (clk)
  --begin
  --  if (clk'event and clk = '1')  then
  --    if temp_buffer_10 /= x"0000000000000000" then
  --      vec_fp <= sine_10 & exp_out_3 & mant;
  --    else
  --      vec_fp <= x"0000000000000000";
  --    end if;
  --  end if;
  --end process save_result;
  
  vec_dp <= sine_12 & exp_out_5 & mant 
              when temp_buffer_12 /= x"0000000000000000" else
	    x"0000000000000000";
 
  sine <= vec_int(63);
  temp_buffer <= '0' & vec_int(62 downto 0);
  exp(10 downto 6) <= b"00000";
  
  find_highest_one_0: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      --temp_int_5_t <= break_apart_t(temp_buffer);
      temp_int_4_3 <= break_apart_3(temp_buffer);
      temp_int_4_2 <= break_apart_2(temp_buffer);
      --temp_int_5_b <= break_apart_b(temp_buffer);
      temp_int_4_1 <= break_apart_1(temp_buffer);
      temp_int_4_0 <= break_apart_0(temp_buffer);
      sine_0 <= sine;
      temp_buffer_0 <= temp_buffer; --just carrying it through 
    end if;
  end process find_highest_one_0;
  
  temp_int_4_3_cond <= temp_int_4_3(15) or temp_int_4_3(14) or temp_int_4_3(13) or temp_int_4_3(12) or
                       temp_int_4_3(11) or temp_int_4_3(10) or temp_int_4_3(9) or temp_int_4_3(8) or
	               temp_int_4_3(7) or temp_int_4_3(6) or temp_int_4_3(5) or temp_int_4_3(4) or
	               temp_int_4_3(3) or temp_int_4_3(2) or temp_int_4_3(1) or temp_int_4_3(0);
  
  temp_int_4_2_cond <= temp_int_4_2(15) or temp_int_4_2(14) or temp_int_4_2(13) or temp_int_4_2(12) or
                       temp_int_4_2(11) or temp_int_4_2(10) or temp_int_4_2(9) or temp_int_4_2(8) or
	               temp_int_4_2(7) or temp_int_4_2(6) or temp_int_4_2(5) or temp_int_4_2(4) or
	               temp_int_4_2(3) or temp_int_4_2(2) or temp_int_4_2(1) or temp_int_4_2(0);
  
  temp_int_4_1_cond <= temp_int_4_1(15) or temp_int_4_1(14) or temp_int_4_1(13) or temp_int_4_1(12) or
                       temp_int_4_1(11) or temp_int_4_1(10) or temp_int_4_1(9) or temp_int_4_1(8) or
	               temp_int_4_1(7) or temp_int_4_1(6) or temp_int_4_1(5) or temp_int_4_1(4) or
	               temp_int_4_1(3) or temp_int_4_1(2) or temp_int_4_1(1) or temp_int_4_1(0);
  
  
  find_highest_one_1_0: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      sine_1_0 <= sine_0;
      temp_buffer_1_0 <= temp_buffer_0;
      temp_int_4_3_cond_reg <= temp_int_4_3_cond;
      temp_int_4_2_cond_reg <= temp_int_4_2_cond;
      temp_int_4_1_cond_reg <= temp_int_4_1_cond;
      temp_int_4_3_reg_0 <= temp_int_4_3;
      temp_int_4_2_reg_0 <= temp_int_4_2;
      temp_int_4_1_reg_0 <= temp_int_4_1;
      temp_int_4_0_reg_0 <= temp_int_4_0;
    end if;
  end process find_highest_one_1_0;
  temp_int_4_3_cond_0 <= temp_int_4_3_cond_reg;
  temp_int_4_3_cond_1 <= temp_int_4_3_cond_reg;
  temp_int_4_3_cond_2 <= temp_int_4_3_cond_reg;
  temp_int_4_3_cond_3 <= temp_int_4_3_cond_reg;
  temp_int_4_3_cond_4 <= temp_int_4_3_cond_reg;
  temp_int_4_3_cond_5 <= temp_int_4_3_cond_reg;
  temp_int_4_2_cond_0 <= temp_int_4_2_cond_reg;
  temp_int_4_2_cond_1 <= temp_int_4_2_cond_reg;
  temp_int_4_2_cond_2 <= temp_int_4_2_cond_reg;
  temp_int_4_2_cond_3 <= temp_int_4_2_cond_reg;
  temp_int_4_2_cond_4 <= temp_int_4_2_cond_reg;
  temp_int_4_2_cond_5 <= temp_int_4_2_cond_reg;
  temp_int_4_1_cond_0 <= temp_int_4_1_cond_reg;
  temp_int_4_1_cond_1 <= temp_int_4_1_cond_reg;
  temp_int_4_1_cond_2 <= temp_int_4_1_cond_reg;
  temp_int_4_1_cond_3 <= temp_int_4_1_cond_reg;
  temp_int_4_1_cond_4 <= temp_int_4_1_cond_reg;
  temp_int_4_1_cond_5 <= temp_int_4_1_cond_reg;
  find_highest_one_1_1: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      sine_1_1 <= sine_1_0;
      temp_buffer_1_1 <= temp_buffer_1_0;
      --temp_int_4_3_cond_reg <= temp_int_4_3_cond;
      --temp_int_4_2_cond_reg <= temp_int_4_2_cond;
      --temp_int_4_1_cond_reg <= temp_int_4_1_cond;
      temp_int_4_3_cond_reg_0 <= temp_int_4_3_cond_0;
      temp_int_4_3_cond_reg_1 <= temp_int_4_3_cond_1;
      temp_int_4_3_cond_reg_2 <= temp_int_4_3_cond_2;
      temp_int_4_3_cond_reg_3 <= temp_int_4_3_cond_3;
      temp_int_4_3_cond_reg_4 <= temp_int_4_3_cond_4;
      temp_int_4_3_cond_reg_5 <= temp_int_4_3_cond_5;
      temp_int_4_2_cond_reg_0 <= temp_int_4_2_cond_0;
      temp_int_4_2_cond_reg_1 <= temp_int_4_2_cond_1;
      temp_int_4_2_cond_reg_2 <= temp_int_4_2_cond_2;
      temp_int_4_2_cond_reg_3 <= temp_int_4_2_cond_3;
      temp_int_4_2_cond_reg_4 <= temp_int_4_2_cond_4;
      temp_int_4_2_cond_reg_5 <= temp_int_4_2_cond_5;
      temp_int_4_1_cond_reg_0 <= temp_int_4_1_cond_0;
      temp_int_4_1_cond_reg_1 <= temp_int_4_1_cond_1;
      temp_int_4_1_cond_reg_2 <= temp_int_4_1_cond_2;
      temp_int_4_1_cond_reg_3 <= temp_int_4_1_cond_3;
      temp_int_4_1_cond_reg_4 <= temp_int_4_1_cond_4;
      temp_int_4_1_cond_reg_5 <= temp_int_4_1_cond_5;
      temp_int_4_3_reg <= temp_int_4_3_reg_0;
      temp_int_4_2_reg <= temp_int_4_2_reg_0;
      temp_int_4_1_reg <= temp_int_4_1_reg_0;
      temp_int_4_0_reg <= temp_int_4_0_reg_0;
    end if;
  end process find_highest_one_1_1;
  
  temp_int_2_3_wire <= break_apart_3(temp_int_4_3_reg) when temp_int_4_3_cond_reg_0 = '1' else
                       break_apart_3(temp_int_4_2_reg) when temp_int_4_2_cond_reg_0 = '1' else
                       break_apart_3(temp_int_4_1_reg) when temp_int_4_1_cond_reg_0 = '1' else
                       break_apart_3(temp_int_4_0_reg);
  temp_int_2_2_wire <= break_apart_2(temp_int_4_3_reg) when temp_int_4_3_cond_reg_1 = '1' else
                       break_apart_2(temp_int_4_2_reg) when temp_int_4_2_cond_reg_1 = '1' else
                       break_apart_2(temp_int_4_1_reg) when temp_int_4_1_cond_reg_1 = '1' else
                       break_apart_2(temp_int_4_0_reg);
  temp_int_2_1_wire <= break_apart_1(temp_int_4_3_reg) when temp_int_4_3_cond_reg_2 = '1' else
                       break_apart_1(temp_int_4_2_reg) when temp_int_4_2_cond_reg_2 = '1' else
                       break_apart_1(temp_int_4_1_reg) when temp_int_4_1_cond_reg_2 = '1' else
                       break_apart_1(temp_int_4_0_reg);
  temp_int_2_0_wire <= break_apart_0(temp_int_4_3_reg) when temp_int_4_3_cond_reg_3 = '1' else
                       break_apart_0(temp_int_4_2_reg) when temp_int_4_2_cond_reg_3 = '1' else
                       break_apart_0(temp_int_4_1_reg) when temp_int_4_1_cond_reg_3 = '1' else
                       break_apart_0(temp_int_4_0_reg);
  shift_cnt_wire <= b"00" when temp_int_4_3_cond_reg_4 = '1' else
                    b"01" when temp_int_4_2_cond_reg_4 = '1' else
                    b"10" when temp_int_4_1_cond_reg_4 = '1' else
                    b"11";
  exp_wire <= b"11" when temp_int_4_3_cond_reg_5 = '1' else
              b"10" when temp_int_4_2_cond_reg_5 = '1' else
              b"01" when temp_int_4_1_cond_reg_5 = '1' else
              b"00";
  
  find_highest_one_1: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      sine_1 <= sine_1_1;
      temp_buffer_1 <= temp_buffer_1_1;
      temp_int_2_3 <= temp_int_2_3_wire;
      temp_int_2_2 <= temp_int_2_2_wire;
      temp_int_2_1 <= temp_int_2_1_wire;
      temp_int_2_0 <= temp_int_2_0_wire;
      shift_cnt(5 downto 4) <= shift_cnt_wire;
      exp(5 downto 4) <= exp_wire;
    end if;
  end process find_highest_one_1;
  
  temp_int_2_3_cond <= temp_int_2_3(3) or temp_int_2_3(2) or temp_int_2_3(1) or temp_int_2_3(0);
  temp_int_2_2_cond <= temp_int_2_2(3) or temp_int_2_2(2) or temp_int_2_2(1) or temp_int_2_2(0);
  temp_int_2_1_cond <= temp_int_2_1(3) or temp_int_2_1(2) or temp_int_2_1(1) or temp_int_2_1(0);
  
  find_highest_one_2_0: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      sine_2_0 <= sine_1;
      temp_buffer_2_0 <= temp_buffer_1;
      shift_cnt_2_0(5 downto 4) <= shift_cnt(5 downto 4);
      shift_cnt_2_0(1 downto 0) <= shift_cnt(1 downto 0);
      exp_2_0(10 downto 4) <= exp(10 downto 4);
      exp_2_0(1 downto 0) <= exp(1 downto 0);
      temp_int_2_3_reg <= temp_int_2_3;
      temp_int_2_2_reg <= temp_int_2_2;
      temp_int_2_1_reg <= temp_int_2_1;
      temp_int_2_0_reg <= temp_int_2_0;
      temp_int_2_3_cond_reg <= temp_int_2_3_cond;
      temp_int_2_2_cond_reg <= temp_int_2_2_cond;
      temp_int_2_1_cond_reg <= temp_int_2_1_cond;
    end if;
  end process find_highest_one_2_0;
  
  temp_int_0_3_wire <= break_apart_3_lg(temp_int_2_3_reg) when temp_int_2_3_cond_reg = '1' else
                       break_apart_3_lg(temp_int_2_2_reg) when temp_int_2_2_cond_reg = '1' else
                       break_apart_3_lg(temp_int_2_1_reg) when temp_int_2_1_cond_reg = '1' else
                       break_apart_3_lg(temp_int_2_0_reg);
  temp_int_0_2_wire <= break_apart_2_lg(temp_int_2_3_reg) when temp_int_2_3_cond_reg = '1' else
                       break_apart_2_lg(temp_int_2_2_reg) when temp_int_2_2_cond_reg = '1' else
                       break_apart_2_lg(temp_int_2_1_reg) when temp_int_2_1_cond_reg = '1' else
                       break_apart_2_lg(temp_int_2_0_reg);
  temp_int_0_1_wire <= break_apart_1_lg(temp_int_2_3_reg) when temp_int_2_3_cond_reg = '1' else
                       break_apart_1_lg(temp_int_2_2_reg) when temp_int_2_2_cond_reg = '1' else
                       break_apart_1_lg(temp_int_2_1_reg) when temp_int_2_1_cond_reg = '1' else
                       break_apart_1_lg(temp_int_2_0_reg);
  temp_int_0_0_wire <= break_apart_0_lg(temp_int_2_3_reg) when temp_int_2_3_cond_reg = '1' else
                       break_apart_0_lg(temp_int_2_2_reg) when temp_int_2_2_cond_reg = '1' else
                       break_apart_0_lg(temp_int_2_1_reg) when temp_int_2_1_cond_reg = '1' else
                       break_apart_0_lg(temp_int_2_0_reg);
  
  shift_cnt_2_wire <= b"00" when temp_int_2_3_cond_reg = '1' else
                      b"01" when temp_int_2_2_cond_reg = '1' else
                      b"10" when temp_int_2_1_cond_reg = '1' else
                      b"11";
  exp_2_wire <= b"11" when temp_int_2_3_cond_reg = '1' else
                b"10" when temp_int_2_2_cond_reg = '1' else
                b"01" when temp_int_2_1_cond_reg = '1' else
                b"00";
  
  find_highest_one_2: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      sine_2 <= sine_2_0;
      temp_buffer_2 <= temp_buffer_2_0;
      shift_cnt_2(5 downto 4) <= shift_cnt_2_0(5 downto 4);
      shift_cnt_2(1 downto 0) <= shift_cnt_2_0(1 downto 0);
      exp_2(10 downto 4) <= exp_2_0(10 downto 4);
      exp_2(1 downto 0) <= exp_2_0(1 downto 0);
      
      temp_int_0_3 <= temp_int_0_3_wire;
      temp_int_0_2 <= temp_int_0_2_wire;
      temp_int_0_1 <= temp_int_0_1_wire;
      temp_int_0_0 <= temp_int_0_0_wire;
      shift_cnt_2(3 downto 2) <= shift_cnt_2_wire;
      exp_2(3 downto 2) <= exp_2_wire;
    end if;
  end process find_highest_one_2;
  
  shift_cnt_3_wire <= b"00" when temp_int_0_3 /= '0' else
                      b"01" when temp_int_0_2 /= '0' else
                      b"10" when temp_int_0_1 /= '0' else
                      b"11";
  exp_3_wire <= b"11" when temp_int_0_3 /= '0' else
                b"10" when temp_int_0_2 /= '0' else
                b"01" when temp_int_0_1 /= '0' else
                b"00";
  
  find_highest_one_3: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      sine_3 <= sine_2;
      temp_buffer_3 <= temp_buffer_2;
      shift_cnt_3(5 downto 2) <= shift_cnt_2(5 downto 2);
      exp_3(10 downto 2) <= exp_2(10 downto 2);
      shift_cnt_3(1 downto 0) <= shift_cnt_3_wire;
      exp_3(1 downto 0) <= exp_3_wire;
    end if;
  end process find_highest_one_3;
end architecture behavioural;



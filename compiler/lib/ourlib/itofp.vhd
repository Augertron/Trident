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

entity mux32 is
  generic (constant pinmap : in integer);
  port(output: out std_logic;
       sel: in std_logic_vector(4 downto 0);
       clk: in std_logic;
       inputs: in std_logic_vector(30 downto 0));
end entity mux32;

architecture mux32_arch of mux32 is
    component mux4 is
      port(output: out std_logic;
	   s0: in std_logic;
	   s1: in std_logic;
	   in0: in std_logic;
	   in1: in std_logic;
	   in2: in std_logic;
	   in3: in std_logic);
    end component mux4;
    component mux2 is
      port(output: out std_logic;
	   s0: in std_logic;
	   in0: in std_logic;
	   in1: in std_logic);
    end component mux2;
    signal inputbuffer: std_logic_vector(31 downto 0);
    signal mux4_0_out: std_logic_vector(0 to 7);
    signal mux4_0_out_reg: std_logic_vector(0 to 7);
    signal mux2_0_out: std_logic_vector(0 to 3);
    signal mux2_0_out_reg: std_logic_vector(0 to 3);
    signal sel_0_3: std_logic_vector(4 downto 0);
    signal sel_0_2: std_logic_vector(4 downto 0);
    signal sel_0_1: std_logic_vector(4 downto 0);
    signal sel_0_0: std_logic_vector(4 downto 0);
    signal sel_1: std_logic_vector(4 downto 0);
    signal sel_2: std_logic_vector(4 downto 0);
    constant pin0 : integer := 31-pinmap;
begin
  input_reg: process (clk)
  begin
    if (clk'event and clk = '1')  then
      inputbuffer(31 downto pin0) <= inputs(pinmap downto 0);
      inputbuffer(30-pinmap downto 0) <= (others => '0');
      sel_0_3 <= sel;
      sel_0_2 <= sel;
      sel_0_1 <= sel;
      sel_0_0 <= sel;
    end if;
  end process input_reg;
  
  mux4_0 : component mux4
    port map(
      output => mux4_0_out(0),
      s0 => sel_0_0(0),
      s1 => sel_0_0(1),
      in0 => inputbuffer(31),
      in1 => inputbuffer(30),
      in2 => inputbuffer(29),
      in3 => inputbuffer(28)
    );
  mux4_1 : component mux4
    port map(
      output => mux4_0_out(1),
      s0 => sel_0_0(0),
      s1 => sel_0_0(1),
      in0 => inputbuffer(27),
      in1 => inputbuffer(26),
      in2 => inputbuffer(25),
      in3 => inputbuffer(24)
    );
  mux4_2 : component mux4
    port map(
      output => mux4_0_out(2),
      s0 => sel_0_1(0),
      s1 => sel_0_1(1),
      in0 => inputbuffer(23),
      in1 => inputbuffer(22),
      in2 => inputbuffer(21),
      in3 => inputbuffer(20)
    );
  mux4_3 : component mux4
    port map(
      output => mux4_0_out(3),
      s0 => sel_0_1(0),
      s1 => sel_0_1(1),
      in0 => inputbuffer(19),
      in1 => inputbuffer(18),
      in2 => inputbuffer(17),
      in3 => inputbuffer(16)
    );
  mux4_4 : component mux4
    port map(
      output => mux4_0_out(4),
      s0 => sel_0_2(0),
      s1 => sel_0_2(1),
      in0 => inputbuffer(15),
      in1 => inputbuffer(14),
      in2 => inputbuffer(13),
      in3 => inputbuffer(12)
    );
  mux4_5 : component mux4
    port map(
      output => mux4_0_out(5),
      s0 => sel_0_2(0),
      s1 => sel_0_2(1),
      in0 => inputbuffer(11),
      in1 => inputbuffer(10),
      in2 => inputbuffer(9),
      in3 => inputbuffer(8)
    );
  mux4_6 : component mux4
    port map(
      output => mux4_0_out(6),
      s0 => sel_0_3(0),
      s1 => sel_0_3(1),
      in0 => inputbuffer(7),
      in1 => inputbuffer(6),
      in2 => inputbuffer(5),
      in3 => inputbuffer(4)
    );
  mux4_7 : component mux4
    port map(
      output => mux4_0_out(7),
      s0 => sel_0_3(0),
      s1 => sel_0_3(1),
      in0 => inputbuffer(3),
      in1 => inputbuffer(2),
      in2 => inputbuffer(1),
      in3 => inputbuffer(0)
    );
    
  middle_reg: process (clk)
  begin
    if (clk'event and clk = '1')  then
      mux4_0_out_reg <= mux4_0_out;
      sel_1 <= sel_0_0;
    end if;
  end process middle_reg;

  mux2_0 : component mux2
    port map(
      output => mux2_0_out(0),
      s0 => sel_1(2),
      in0 => mux4_0_out_reg(0),
      in1 => mux4_0_out_reg(1)
    );
  mux2_1 : component mux2
    port map(
      output => mux2_0_out(1),
      s0 => sel_1(2),
      in0 => mux4_0_out_reg(2),
      in1 => mux4_0_out_reg(3)
    );
  mux2_2 : component mux2
    port map(
      output => mux2_0_out(2),
      s0 => sel_1(2),
      in0 => mux4_0_out_reg(4),
      in1 => mux4_0_out_reg(5)
    );
  mux2_3 : component mux2
    port map(
      output => mux2_0_out(3),
      s0 => sel_1(2),
      in0 => mux4_0_out_reg(6),
      in1 => mux4_0_out_reg(7)
    );

  last_reg: process (clk)
  begin
    if (clk'event and clk = '1')  then
      mux2_0_out_reg <= mux2_0_out;
      sel_2 <= sel_1;
    end if;
  end process last_reg;

  mux4_8 : component mux4
    port map(
      output => output,
      s0 => sel_2(3),
      s1 => sel_2(4),
      in0 => mux2_0_out_reg(0),
      in1 => mux2_0_out_reg(1),
      in2 => mux2_0_out_reg(2),
      in3 => mux2_0_out_reg(3)
    );
  
end architecture mux32_arch;

library ieee;

use ieee.std_logic_unsigned.all;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

entity log_shift_left_32 is
  port(data_in: in std_logic_vector(30 downto 0);
       shift_cnt: in std_logic_vector(4 downto 0);
       data_out: out std_logic_vector(30 downto 0);
       clk: in std_logic);
end entity log_shift_left_32;

architecture log_shift_left_32_arch of log_shift_left_32 is
   component mux32 is
     generic (constant pinmap : in integer);
     port(output: out std_logic;
          sel: in std_logic_vector(4 downto 0);
	  clk: in std_logic;
          inputs: in std_logic_vector(30 downto 0));
   end component mux32;
   signal out_buffer: std_logic_vector(30 downto 0);
   signal input_buffer_0: std_logic_vector(30 downto 0);
   signal input_buffer_1: std_logic_vector(30 downto 0);
   signal input_buffer_2: std_logic_vector(30 downto 0);
   signal input_buffer_3: std_logic_vector(30 downto 0);
   signal shift_cnt_0: std_logic_vector(4 downto 0);
   --signal z_net: std_logic_vector(31 downto 0);
   --signal buffer2: std_logic_vector(5 downto 0);
   --signal buffer3: std_logic_vector(30 downto 0);
begin
  divide_data: process (clk) is 
  begin
      if  (clk'event and clk = '1')  then
	input_buffer_0 <= data_in;
	input_buffer_1 <= data_in;
	input_buffer_2 <= data_in;
	input_buffer_3 <= data_in;
	shift_cnt_0 <= shift_cnt;
      end if;
  end process divide_data;

  foreachpin_0: for pin0 in 0 to 7 generate
   --constant middlePoint: integer := 30 - pin0; 
   --constant middlePoint1: integer := 30 - pin0 - 1; 
  begin
    --foreachpin1: for pin1 in pin0 downto pin0 - data_in'high generate
    --begin
      --mappin: if pin0 - data_in'high >= 0 generate
      --begin
	--z_net <= (others => '0');
	mux32_shift: component mux32
	  generic map (pinmap => pin0)
	  port map ( output => out_buffer(pin0),
	             clk => clk,
		     inputs(30 downto 0) => 
		     	  input_buffer_0(30 downto 0),
	             sel => shift_cnt_0);
		     --inputs(middlePoint1 downto 0) => 
		     --	  z_net(middlePoint1 downto 0));
		     --inputs => data_in(pin0 downto 0) & (others => '0'));
      --end generate mappin;
    --end generate foreachpin1;
  end generate foreachpin_0;
  foreachpin_1: for pin0 in 8 to 15 generate
  begin
	mux32_shift: component mux32
	  generic map (pinmap => pin0)
	  port map ( output => out_buffer(pin0),
	             clk => clk,
		     inputs(30 downto 0) => 
		     	  input_buffer_1(30 downto 0),
	             sel => shift_cnt_0);
  end generate foreachpin_1;
  foreachpin_2: for pin0 in 16 to 23 generate
  begin
	mux32_shift: component mux32
	  generic map (pinmap => pin0)
	  port map ( output => out_buffer(pin0),
	             clk => clk,
		     inputs(30 downto 0) => 
		     	  input_buffer_2(30 downto 0),
	             sel => shift_cnt_0);
  end generate foreachpin_2;
  foreachpin_3: for pin0 in 24 to 30 generate
  begin
	mux32_shift: component mux32
	  generic map (pinmap => pin0)
	  port map ( output => out_buffer(pin0),
	             clk => clk,
		     inputs(30 downto 0) => 
		     	  input_buffer_3(30 downto 0),
	             sel => shift_cnt_0);
  end generate foreachpin_3;
  store_data: process (clk) is 
  begin
      if  (clk'event and clk = '1')  then
        data_out <= out_buffer;
  --data_out <= out_buffer when i_start = '1' else (others => '0');
      end if;
  end process store_data;
  
end architecture log_shift_left_32_arch;

library ieee;

use ieee.std_logic_unsigned.all;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

entity i_to_fp is
  port(vec_int: in std_logic_vector(31 downto 0);
       vec_fp:out std_logic_vector(31 downto 0);
       clk: in std_logic);
end entity i_to_fp;

architecture behavioural of i_to_fp is

--======================================================--
--                     FUNCTIONS                        --
--======================================================--
	--function to convert an integer into a std_logic_vector
	function int_to_std_lg_vec (input: integer) 
	              return std_logic_vector is
		variable ans : std_logic_vector(4 downto 0); 
		variable int : integer := input;
	begin
		conv: for i in 0 to 4 loop
		  if int mod 2 = 1 then
		    ans(i) := '1';
		  else
		    ans(i) := '0';
		  end if;
		  int := int / 2;
		end loop conv;
		return ans; 
	end function int_to_std_lg_vec;
	--function 
	function break_apart_t_lg (input: std_logic_vector) 
	              return std_logic is
		variable ans : std_logic; 
		--variable int : integer;
		--variable cntr : integer := 0;
	begin
		--break_up: for i in 0 to 2 loop
		  --int := i / (input'high/2 + 1);
		  --if int mod 2 = 1 then
		    ans := input(1);
		    --cntr := cntr + 1;
		  --end if;
		--end loop break_up;
		return ans; 
	end function break_apart_t_lg;
	--function 
	function break_apart_t (input: std_logic_vector) 
	              return std_logic_vector is
		variable ans : std_logic_vector(input'high/2 downto 0); 
		--variable int : integer;
		variable cntr : integer := input'high/2;
	begin
		break_up: for i in input'high downto input'high/2+1 loop
		  --int := i / (input'high/2 + 1);
		  --if int mod 2 = 1 then
		    ans(cntr) := input(i);
		    cntr := cntr - 1;
		  --end if;
		end loop break_up;
		return ans; 
	end function break_apart_t;
	--function 
	function break_apart_b (input: std_logic_vector) 
	              return std_logic_vector is
		variable ans : std_logic_vector(input'high/2 downto 0); 
		--variable int : integer;
		variable cntr : integer := input'high/2;
	begin
		break_up: for i in input'high/2 downto 0 loop
		  --int := i / (input'high/2 + 1);
		  --if int mod 2 = 0 then
		    ans(cntr) := input(i);
		    cntr := cntr - 1;
		  --end if;
		end loop break_up;
		return ans; 
	end function break_apart_b;
	--function 
	function break_apart_4 (input: std_logic_vector) 
	              return std_logic_vector is
		variable ans : std_logic_vector(14 downto 0); 
		variable int : integer;
		variable cntr : integer := 0;
	begin
		break_up: for i in 0 to 30 loop
		  int := i / 16;
		  if int mod 2 = 1 then
		    ans(cntr) := input(i);
		    cntr := cntr + 1;
		  end if;
		end loop break_up;
		return ans; 
	end function break_apart_4;
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

    component log_shift_left_32 is
      port(data_in: in std_logic_vector(30 downto 0);
           shift_cnt: in std_logic_vector(4 downto 0);
           data_out: out std_logic_vector(30 downto 0);
	   clk: in std_logic);
    end component log_shift_left_32;
    
    
    signal mant: std_logic_vector(22 downto 0);
    signal sine: std_logic;
    signal sine_0: std_logic;
    signal sine_1: std_logic;
    signal sine_2: std_logic;
    signal sine_2_0: std_logic;
    signal sine_2_1: std_logic;
    signal sine_3: std_logic;
    signal sine_4: std_logic;
    signal sine_5: std_logic;
    signal sine_6: std_logic;
    signal sine_7: std_logic;
    signal sine_8: std_logic;
    signal sine_9: std_logic;
    signal sine_10: std_logic;
    signal start: std_logic;
    signal no_read: std_logic;
    signal temp_buffer: std_logic_vector(31 downto 0);
    signal temp_buffer_0: std_logic_vector(31 downto 0);
    signal temp_buffer_1: std_logic_vector(31 downto 0);
    signal temp_buffer_2: std_logic_vector(31 downto 0);
    signal temp_buffer_2_0: std_logic_vector(31 downto 0);
    signal temp_buffer_2_1: std_logic_vector(31 downto 0);
    signal temp_buffer_3: std_logic_vector(31 downto 0);
    signal temp_buffer_4: std_logic_vector(31 downto 0);
    signal temp_buffer_5: std_logic_vector(31 downto 0);
    signal temp_buffer_6: std_logic_vector(31 downto 0);
    signal temp_buffer_7: std_logic_vector(31 downto 0);
    signal temp_buffer_8: std_logic_vector(31 downto 0);
    signal temp_buffer_9: std_logic_vector(31 downto 0);
    signal temp_buffer_10: std_logic_vector(31 downto 0);
    --signal temp_int_4: std_logic_vector(14 downto 0);
    signal temp_int_4_t: std_logic_vector(15 downto 0);
    signal temp_int_4_b: std_logic_vector(15 downto 0);
    signal temp_int_4_t_0: std_logic_vector(15 downto 0);
    signal temp_int_4_t_0_tmp: std_logic_vector(15 downto 0);
    signal temp_int_4_t_0_tmp_0: std_logic_vector(15 downto 0);
    signal temp_int_4_t_0_0: std_logic_vector(15 downto 0);
    signal temp_int_4_t_0_1: std_logic_vector(15 downto 0);
    signal temp_int_4_t_0_2: std_logic_vector(15 downto 0);
    signal temp_int_4_t_0_3: std_logic_vector(15 downto 0);
    signal temp_int_4_b_0: std_logic_vector(15 downto 0);
    signal temp_int_4_b_0_tmp: std_logic_vector(15 downto 0);
    signal temp_int_4_b_0_tmp_0: std_logic_vector(15 downto 0);
    signal temp_int_4_3: std_logic_vector(3 downto 0);
    signal temp_int_4_2: std_logic_vector(3 downto 0);
    signal temp_int_4_1: std_logic_vector(3 downto 0);
    signal temp_int_4_0: std_logic_vector(3 downto 0);
    signal temp_int_3_t: std_logic_vector(7 downto 0);
    signal temp_int_3_t_0: std_logic_vector(7 downto 0);
    signal temp_int_3_t_1: std_logic_vector(7 downto 0);
    signal temp_int_3_t_wire: std_logic_vector(7 downto 0);
    signal temp_int_3_t_wire_t_tmp: std_logic_vector(7 downto 0);
    signal temp_int_3_t_wire_b_tmp: std_logic_vector(7 downto 0);
    signal temp_int_3_b: std_logic_vector(7 downto 0);
    signal temp_int_3_b_wire: std_logic_vector(7 downto 0);
    signal temp_int_3_b_wire_t_tmp: std_logic_vector(7 downto 0);
    signal temp_int_3_b_wire_b_tmp: std_logic_vector(7 downto 0);
    signal temp_int_2_3: std_logic_vector(1 downto 0);
    signal temp_int_2_3_wire: std_logic_vector(1 downto 0);
    signal temp_int_2_2: std_logic_vector(1 downto 0);
    signal temp_int_2_2_wire: std_logic_vector(1 downto 0);
    signal temp_int_2_1: std_logic_vector(1 downto 0);
    signal temp_int_2_1_wire: std_logic_vector(1 downto 0);
    signal temp_int_2_0: std_logic_vector(1 downto 0);
    signal temp_int_2_0_wire: std_logic_vector(1 downto 0);
    --signal temp_int_1_t: std_logic_vector(1 downto 0);
    --signal temp_int_1_b: std_logic_vector(1 downto 0);
    signal temp_int_0_t: std_logic;
    signal temp_int_0_t_wire: std_logic;
    signal shift_cnt: std_logic_vector(4 downto 0);
    signal shift_cnt_wire: std_logic;
    signal shift_cnt_wire_reg: std_logic;
    signal shift_cnt_wire_reg_0: std_logic;
    signal shift_cnt_1: std_logic_vector(4 downto 0);
    signal shift_cnt_1_wire: std_logic;
    signal shift_cnt_1_wire_0: std_logic;
    signal shift_cnt_1_wire_0_reg: std_logic;
    signal shift_cnt_1_wire_1: std_logic;
    signal shift_cnt_1_wire_1_reg: std_logic;
    signal shift_cnt_2: std_logic_vector(4 downto 0);
    signal shift_cnt_2_wire: std_logic_vector(1 downto 0);
    --signal shift_cnt_3: std_logic_vector(4 downto 0);
    --signal shift_cnt_4: std_logic_vector(4 downto 0);
    signal shift_cnt_5: std_logic_vector(4 downto 0);
    signal shift_cnt_5_wire: std_logic;
    signal exp: std_logic_vector(7 downto 0);
    signal exp_wire: std_logic;
    signal exp_wire_reg: std_logic;
    signal exp_wire_reg_tmp: std_logic;
    signal exp_wire_reg_0: std_logic;
    signal exp_wire_reg_0_reg: std_logic;
    signal exp_wire_reg_1: std_logic;
    signal exp_wire_reg_1_reg: std_logic;
    signal exp_wire_reg_2: std_logic;
    signal exp_wire_reg_2_reg: std_logic;
    signal exp_wire_reg_3: std_logic;
    signal exp_wire_reg_3_reg: std_logic;
    signal exp_wire_reg_4: std_logic;
    signal exp_wire_reg_4_reg: std_logic;
    signal exp_wire_reg_5: std_logic;
    signal exp_wire_reg_5_reg: std_logic;
    signal exp_wire_reg_6: std_logic;
    signal exp_wire_reg_6_reg: std_logic;
    signal exp_wire_reg_7: std_logic;
    signal exp_wire_reg_7_reg: std_logic;
    signal exp_1: std_logic_vector(7 downto 0);
    signal exp_1_wire: std_logic;
    signal exp_1_wire_0: std_logic;
    signal exp_1_wire_0_reg: std_logic;
    signal exp_1_wire_1: std_logic;
    signal exp_1_wire_1_reg: std_logic;
    signal exp_2: std_logic_vector(7 downto 0);
    signal exp_2_wire: std_logic_vector(1 downto 0);
    --signal exp_3: std_logic_vector(7 downto 0);
    --signal exp_4: std_logic_vector(7 downto 0);
    signal exp_5_bob: std_logic_vector(7 downto 0);
    signal exp_5_bob_wire: std_logic;
    signal exp_5: std_logic_vector(7 downto 0);
    signal exp_5_wire: std_logic;
    signal temp: std_logic_vector(7 downto 0);
    --signal exp1: std_logic_vector(7 downto 0);
    signal exp_out: std_logic_vector(7 downto 0);
    signal exp_out_reg_0: std_logic_vector(7 downto 0);
    signal exp_out_reg_1: std_logic_vector(7 downto 0);
    signal exp_out_1: std_logic_vector(7 downto 0);
    signal exp_out_2: std_logic_vector(7 downto 0);
    signal exp_out_3: std_logic_vector(7 downto 0);
    signal exp_out_4: std_logic_vector(7 downto 0);
begin
  shftlftlog_32 : component log_shift_left_32
    port map(
      data_in => temp_buffer_5(30 downto 0), 
      data_out(30 downto 8) => mant,
      data_out(7 downto 0) => temp,
      shift_cnt => shift_cnt_5,
      clk => clk
    );
  
  exp_add: process (clk)
  begin
    if (clk'event and clk = '1')  then
      --exp_out <= exp_5_bob + x"7F";
      exp_out_reg_0 <= exp_5_bob;
      exp_out_reg_1 <= exp_5_bob;
      sine_6 <= sine_5;
      temp_buffer_6 <= temp_buffer_5;
    end if;
  end process exp_add;
  
  exp_out(7 downto 1) <= '1' & exp_out_reg_0(6 downto 1) 
                                 when exp_out_reg_1(0) = '1' else
			  '1' & exp_out_reg_0(6 downto 2) & not exp_out_reg_0(1)
			         when exp_out_reg_1(1) = '1' else
			  '1' & exp_out_reg_0(6 downto 3) & not exp_out_reg_0(2 downto 1)
			         when exp_out_reg_1(2) = '1' else
			  '1' & exp_out_reg_0(6 downto 4) & not exp_out_reg_0(3 downto 1)
			         when exp_out_reg_1(3) = '1' else
			  '1' & exp_out_reg_0(6 downto 5) & not exp_out_reg_0(4 downto 1)
			         when exp_out_reg_1(4) = '1' else
			  '0' & not exp_out_reg_0(6 downto 1);
  exp_out(0) <= not exp_out_reg_0(0);
  
  save_1: process (clk)
  begin
    if (clk'event and clk = '1')  then
      exp_out_1 <= exp_out;
      sine_7 <= sine_6;
      temp_buffer_7 <= temp_buffer_6;
    end if;
  end process save_1;
  
  save_2: process (clk)
  begin
    if (clk'event and clk = '1')  then
      exp_out_2 <= exp_out_1;
      sine_8 <= sine_7;
      temp_buffer_8 <= temp_buffer_7;
    end if;
  end process save_2;
  
  save_3: process (clk)
  begin
    if (clk'event and clk = '1')  then
      exp_out_3 <= exp_out_2;
      sine_9 <= sine_8;
      temp_buffer_9 <= temp_buffer_8;
    end if;
  end process save_3;
  
  save_4: process (clk)
  begin
    if (clk'event and clk = '1')  then
      exp_out_4 <= exp_out_3;
      sine_10 <= sine_9;
      temp_buffer_10 <= temp_buffer_9;
    end if;
  end process save_4;
  
  --save_result: process (clk)
  --begin
  --  if (clk'event and clk = '1')  then
  --    if temp_buffer_8 /= x"00000000" then
  --      vec_fp <= sine_8 & exp_out_2 & mant;
  --    else
  --      vec_fp <= x"00000000";
  --    end if;
  --  end if;
  --end process save_result;
  
  vec_fp <= sine_10 & exp_out_4 & mant 
               when temp_buffer_10 /= x"00000000" else
            x"00000000";
	    
  sine <= vec_int(31);
  temp_buffer <= '0' & vec_int(30 downto 0);
  exp(7 downto 5) <= b"000";
  
  
  find_highest_one_0: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      temp_int_4_t <= break_apart_t(temp_buffer);
      temp_int_4_b <= break_apart_b(temp_buffer);
      temp_buffer_0 <= temp_buffer; --just carrying it through 
      sine_0 <= sine;
      exp(3 downto 0) <= b"0000";
    end if;
  end process find_highest_one_0;
  
  find_highest_one_1: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      temp_int_4_3 <= temp_int_4_t(15 downto 12);
      temp_int_4_2 <= temp_int_4_t(11 downto 8);
      temp_int_4_1 <= temp_int_4_t(7 downto 4);
      temp_int_4_0 <= temp_int_4_t(3 downto 0);
      temp_int_4_t_0 <= temp_int_4_t;
      temp_int_4_t_0_0 <= temp_int_4_t;
      temp_int_4_t_0_1 <= temp_int_4_t;
      temp_int_4_t_0_2 <= temp_int_4_t;
      temp_int_4_t_0_3 <= temp_int_4_t;
      temp_int_4_b_0 <= temp_int_4_b;
      temp_buffer_1 <= temp_buffer_0; 
      sine_1 <= sine_0;
    end if;
  end process find_highest_one_1;
  
  shift_cnt_wire <= not temp_int_4_t_0_2(15) and not temp_int_4_t_0_2(14) and not temp_int_4_t_0_2(13) and not temp_int_4_t_0_2(12) and
                    not temp_int_4_t_0_2(11) and not temp_int_4_t_0_2(10) and not temp_int_4_t_0_2(9) and not temp_int_4_t_0_2(8) and 
	            not temp_int_4_t_0_2(7) and not temp_int_4_t_0_2(6) and not temp_int_4_t_0_2(5) and not temp_int_4_t_0_2(4) and 
	            not temp_int_4_t_0_2(3) and not temp_int_4_t_0_2(2) and not temp_int_4_t_0_2(1) and not temp_int_4_t_0_2(0);
  
  exp_wire <= temp_int_4_t_0_3(15) or temp_int_4_t_0_3(14) or temp_int_4_t_0_3(13) or temp_int_4_t_0_3(12) or
              temp_int_4_t_0_3(11) or temp_int_4_t_0_3(10) or temp_int_4_t_0_3(9) or temp_int_4_t_0_3(8) or
	      temp_int_4_t_0_3(7) or temp_int_4_t_0_3(6) or temp_int_4_t_0_3(5) or temp_int_4_t_0_3(4) or
	      temp_int_4_t_0_3(3) or temp_int_4_t_0_3(2) or temp_int_4_t_0_3(1) or temp_int_4_t_0_3(0);
  
  find_highest_one_2_0: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      temp_buffer_2_0 <= temp_buffer_1;  
      sine_2_0 <= sine_1;
      shift_cnt_wire_reg <= shift_cnt_wire;
      exp_wire_reg <= exp_wire;
      temp_int_4_t_0_tmp <= temp_int_4_t_0;
      temp_int_4_b_0_tmp <= temp_int_4_b_0;
    end if;
  end process find_highest_one_2_0;
  
  exp_wire_reg_0 <= exp_wire_reg;
  exp_wire_reg_1 <= exp_wire_reg;
  exp_wire_reg_2 <= exp_wire_reg;
  exp_wire_reg_3 <= exp_wire_reg;
  exp_wire_reg_4 <= exp_wire_reg;
  exp_wire_reg_5 <= exp_wire_reg;
  exp_wire_reg_6 <= exp_wire_reg;
  exp_wire_reg_7 <= exp_wire_reg;

  find_highest_one_2_0_0: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      temp_buffer_2_1 <= temp_buffer_2_0;  
      sine_2_1 <= sine_2_0;
      shift_cnt_wire_reg_0 <= shift_cnt_wire_reg;
      exp_wire_reg_tmp <= exp_wire_reg;
      temp_int_4_t_0_tmp_0 <= temp_int_4_t_0_tmp;
      temp_int_4_b_0_tmp_0 <= temp_int_4_b_0_tmp;
      exp_wire_reg_0_reg <= exp_wire_reg_0;
      exp_wire_reg_1_reg <= exp_wire_reg_1;
      exp_wire_reg_2_reg <= exp_wire_reg_2;
      exp_wire_reg_3_reg <= exp_wire_reg_3;
      exp_wire_reg_4_reg <= exp_wire_reg_4;
      exp_wire_reg_5_reg <= exp_wire_reg_5;
      exp_wire_reg_6_reg <= exp_wire_reg_6;
      exp_wire_reg_7_reg <= exp_wire_reg_7;
    end if;
  end process find_highest_one_2_0_0;


  temp_int_3_t_wire_t_tmp <= break_apart_t(temp_int_4_t_0_tmp_0);
  temp_int_3_t_wire_b_tmp <= break_apart_t(temp_int_4_b_0_tmp_0);
  
  temp_int_3_t_wire(1 downto 0) <= temp_int_3_t_wire_t_tmp(1 downto 0) when 
                   		      exp_wire_reg_0_reg = '1' else
		        	   temp_int_3_t_wire_b_tmp(1 downto 0);
  temp_int_3_t_wire(3 downto 2) <= temp_int_3_t_wire_t_tmp(3 downto 2) when 
                   		      exp_wire_reg_1_reg = '1' else
		        	   temp_int_3_t_wire_b_tmp(3 downto 2);
  temp_int_3_t_wire(5 downto 4) <= temp_int_3_t_wire_t_tmp(5 downto 4) when 
                   		      exp_wire_reg_2_reg = '1' else
		        	   temp_int_3_t_wire_b_tmp(5 downto 4);
  temp_int_3_t_wire(7 downto 6) <= temp_int_3_t_wire_t_tmp(7 downto 6) when 
                                        exp_wire_reg_3_reg = '1' else
				     temp_int_3_t_wire_b_tmp(7 downto 6);

  
  temp_int_3_b_wire_t_tmp <= break_apart_b(temp_int_4_t_0_tmp_0);
  temp_int_3_b_wire_b_tmp <= break_apart_b(temp_int_4_b_0_tmp_0);

  temp_int_3_b_wire(1 downto 0) <= temp_int_3_b_wire_t_tmp(1 downto 0) when 
                                        exp_wire_reg_4_reg = '1' else
				     temp_int_3_b_wire_b_tmp(1 downto 0);
  temp_int_3_b_wire(3 downto 2) <= temp_int_3_b_wire_t_tmp(3 downto 2) when 
                                        exp_wire_reg_5_reg = '1' else
				     temp_int_3_b_wire_b_tmp(3 downto 2);
  temp_int_3_b_wire(5 downto 4) <= temp_int_3_b_wire_t_tmp(5 downto 4) when 
                                        exp_wire_reg_6_reg = '1' else
				     temp_int_3_b_wire_b_tmp(5 downto 4);
  temp_int_3_b_wire(7 downto 6) <= temp_int_3_b_wire_t_tmp(7 downto 6) when 
                                        exp_wire_reg_7_reg = '1' else
				     temp_int_3_b_wire_b_tmp(7 downto 6);
  
  

  find_highest_one_2_1: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      temp_buffer_2 <= temp_buffer_2_1;  
      sine_2 <= sine_2_1;
      temp_int_3_t <= temp_int_3_t_wire;
      temp_int_3_t_0 <= temp_int_3_t_wire;
      temp_int_3_t_1 <= temp_int_3_t_wire;
      temp_int_3_b <= temp_int_3_b_wire;
      shift_cnt(4) <= shift_cnt_wire_reg_0;
      exp(4) <= exp_wire_reg_tmp;
    end if;
  end process find_highest_one_2_1;
  
  
  temp_int_2_3_wire <= break_apart_3(temp_int_3_t) when temp_int_3_t /= x"00" else
                       break_apart_3(temp_int_3_b);
  temp_int_2_2_wire <= break_apart_2(temp_int_3_t) when temp_int_3_t /= x"00" else
                       break_apart_2(temp_int_3_b);
  temp_int_2_1_wire <= break_apart_1(temp_int_3_t) when temp_int_3_t /= x"00" else
                       break_apart_1(temp_int_3_b);
  temp_int_2_0_wire <= break_apart_0(temp_int_3_t) when temp_int_3_t /= x"00" else
                       break_apart_0(temp_int_3_b);
  
  shift_cnt_1_wire_0 <= not temp_int_3_t_0(7) and not temp_int_3_t_0(6) and not temp_int_3_t_0(5) and not temp_int_3_t_0(4); 
  shift_cnt_1_wire_1 <= not temp_int_3_t_0(3) and not temp_int_3_t_0(2) and not temp_int_3_t_0(1) and not temp_int_3_t_0(0);
  exp_1_wire_0 <= temp_int_3_t_1(7) or temp_int_3_t_1(6) or temp_int_3_t_1(5) or temp_int_3_t_1(4);
  exp_1_wire_1 <= temp_int_3_t_1(3) or temp_int_3_t_1(2) or temp_int_3_t_1(1) or temp_int_3_t_1(0);
  
  find_highest_one_3: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      --shift_cnt_1(3) <= shift_cnt_1_wire;
      shift_cnt_1_wire_0_reg <= shift_cnt_1_wire_0;
      shift_cnt_1_wire_1_reg <= shift_cnt_1_wire_1;
      exp_1_wire_0_reg <= exp_1_wire_0;
      exp_1_wire_1_reg <= exp_1_wire_1;
      --exp_1(3) <= exp_1_wire;
      temp_int_2_3 <= temp_int_2_3_wire;
      temp_int_2_2 <= temp_int_2_2_wire;
      temp_int_2_1 <= temp_int_2_1_wire;
      temp_int_2_0 <= temp_int_2_0_wire;
      temp_buffer_3 <= temp_buffer_2;  
      sine_3 <= sine_2;
      shift_cnt_1(2 downto 0) <= shift_cnt(2 downto 0);
      shift_cnt_1(4) <= shift_cnt(4);
      exp_1(7 downto 4) <= exp(7 downto 4);
      exp_1(2 downto 0) <= exp(2 downto 0);
      
    end if;
  end process find_highest_one_3;
  
  temp_int_0_t_wire <= break_apart_t_lg(temp_int_2_3) when temp_int_2_3 /= b"00" else
                       break_apart_t_lg(temp_int_2_2) when temp_int_2_2 /= b"00" else
		       break_apart_t_lg(temp_int_2_1) when temp_int_2_1 /= b"00" else
		       break_apart_t_lg(temp_int_2_0);
  shift_cnt_2_wire <= b"00" when temp_int_2_3 /= b"00" else
                      b"01" when temp_int_2_2 /= b"00" else
		      b"10" when temp_int_2_1 /= b"00" else
		      b"11";
  exp_2_wire <= b"11" when temp_int_2_3 /= b"00" else
                b"10" when temp_int_2_2 /= b"00" else
		b"01" when temp_int_2_1 /= b"00" else
		b"00";
  shift_cnt_1_wire <= shift_cnt_1_wire_0_reg and shift_cnt_1_wire_1_reg;
  exp_1_wire <= exp_1_wire_0_reg or exp_1_wire_1_reg;

  find_highest_one_4: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      temp_buffer_4 <= temp_buffer_3;  
      sine_4 <= sine_3;
      shift_cnt_2(0) <= shift_cnt_1(0);
      shift_cnt_2(4) <= shift_cnt_1(4);
      shift_cnt_2(3) <= shift_cnt_1_wire;
      exp_2(3) <= exp_1_wire;
      exp_2(7 downto 4) <= exp_1(7 downto 4);
      exp_2(0) <= exp(0);
      
      temp_int_0_t <= temp_int_0_t_wire;
      shift_cnt_2(2 downto 1) <= shift_cnt_2_wire;
      exp_2(2 downto 1) <= exp_2_wire;
    end if;
  end process find_highest_one_4;
  
  shift_cnt_5_wire <= '0' when temp_int_0_t /= '0' else
                      '1';
  exp_5_bob_wire <= '1' when temp_int_0_t /= '0' else
                '0';
  
  find_highest_one_5: process (clk) is
  begin
    if (clk'event and clk = '1')  then
      shift_cnt_5(4 downto 1) <= shift_cnt_2(4 downto 1);
      shift_cnt_5(0) <= shift_cnt_5_wire;
      exp_5_bob(7 downto 1) <= exp_2(7 downto 1);
      exp_5_bob(0) <= exp_5_bob_wire;
      sine_5 <= sine_4;
      temp_buffer_5 <= temp_buffer_4;
    end if;
  end process find_highest_one_5;
end architecture behavioural;




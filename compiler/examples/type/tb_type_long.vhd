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


--  This file was generated automatically.
--  VHDL built by VHDLGen version 0.12
--  written by Justin L. Tripp
--  Copyright (c) 2005
-- 
--  ---------------------------------------- 
--  FileName           :
--  ModelName          :
--  Title              :
--  Purpose            :
--  Author(s)          :
--  Comment            :
--  ---------------------------------------- 
--  Version  | Author  | Date     | Changes  
--  ---------------------------------------- 
--  1.0      | VHDLgen |          | initial  
--  ---------------------------------------- 
-- 
--  Generated on Thu Sep 15 12:56:31 MDT 2005


library ieee, ieee_proposed, std_developerskit;

use ieee.std_logic_unsigned.all;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

use ieee_proposed.fphdl_base_pkg.all;

use std_developerskit.std_iopak.all;


entity tb_trident_design is
end entity tb_trident_design;

architecture tb_trident_design_arch of tb_trident_design is

   component trident_design is
      port(
         signal o_done : out std_logic;
         signal o_a : out std_logic_vector(31 downto 0); --why is long the same size as int?
         signal reset : in std_logic;
         signal i_a_we : in std_logic;
         signal i_a : in std_logic_vector(31 downto 0);
         signal i_b_we : in std_logic;
         signal i_b : in std_logic_vector(31 downto 0);
         signal o_b : out std_logic_vector(31 downto 0);
         signal start : in std_logic;
         signal clk : in std_logic
      );
   end component trident_design;
   constant PERIOD : time := 10 ns;
   signal w_i_b_we : std_logic;
   signal w_clk : std_logic := '0';
   signal w_i_a : std_logic_vector(31 downto 0);
   signal w_o_a : std_logic_vector(31 downto 0);
   signal w_o_b : std_logic_vector(31 downto 0);
   signal w_start : std_logic;
   signal w_i_b : std_logic_vector(31 downto 0);
   signal w_o_done : std_logic;
   signal w_i_a_we : std_logic;
   signal w_reset : std_logic;
-- begin architecture for tb_trident_design
begin
   dut : component trident_design
      port map(
         o_done => w_o_done,
         o_a => w_o_a,
         reset => w_reset,
         i_a_we => w_i_a_we,
         i_a => w_i_a,
         i_b_we => w_i_b_we,
         i_b => w_i_b,
         o_b => w_o_b,
         start => w_start,
         clk => w_clk
      );
   w_clk <= not w_clk after PERIOD / 2 ;
   STIMULI:
      process  is
      begin
         --test 1st condition:
	 
	 w_reset <= '0' ;
         w_start <= '0' ;
         w_i_a <= (others => '0');
         w_i_a_we <= '0';
         w_i_b <= (others => '0');
         w_i_b_we <= '0';
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
         w_i_a <= x"FFFFFFFB";
         w_i_a_we <= '1';
         w_i_b <= x"00000008";
         w_i_b_we <= '1';
         wait for PERIOD;
         w_i_a_we <= '0';
         w_i_b_we <= '0';
         w_start <= '1';
         wait for PERIOD;
         w_start <= '0';

         wait until w_o_done = '1';
         wait for PERIOD;
         
         --assert false
         --  report "Done signal found"
         --  severity note;
         assert w_o_a = x"00000009"
           report "A wasn't set correctly to 9.0 "& to_string(w_o_a)
             &" tb_type_long.vhd."
           severity error;
         assert w_o_b = x"0000000B"
           report "B wasn't set correctly to 11.0 "& to_string(w_o_b)
             &" tb_type_long.vhd."
           severity error;
         
	 --===================================================================
         --test 2nd condition:
	 
	 w_reset <= '0' ;
         w_start <= '0' ;
         w_i_a <= (others => '0');
         w_i_a_we <= '0';
         w_i_b <= (others => '0');
         w_i_b_we <= '0';
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
         w_i_a <= x"00000006";
         w_i_a_we <= '1';
         w_i_b <= x"fffffff2";
         w_i_b_we <= '1';
         wait for PERIOD;
         w_i_a_we <= '0';
         w_i_b_we <= '0';
         w_start <= '1';
         wait for PERIOD;
         w_start <= '0';

         wait until w_o_done = '1';
         wait for PERIOD;
         
         --assert false
         --  report "Done signal found"
         --  severity note;
         assert w_o_a = x"fffffff3"
           report "A wasn't set correctly to -13.0 "& to_string(w_o_a)
             &" tb_type_long.vhd."
           severity error;
         assert w_o_b = x"fffffff5"
           report "B wasn't set correctly to -11.0 "& to_string(w_o_b)
             &" tb_type_long.vhd."
           severity error;
         
	 --===================================================================
	 --test 3rd condition
         wait for PERIOD*3;
	 
	 w_reset <= '0' ;
         w_start <= '0' ;
         w_i_a <= (others => '0');
         w_i_a_we <= '0';
         w_i_b <= (others => '0');
         w_i_b_we <= '0';
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
         w_i_a <= x"00000016";
         w_i_a_we <= '1';
         w_i_b <= x"00000015";
         w_i_b_we <= '1';
         wait for PERIOD;
         w_i_a_we <= '0';
         w_i_b_we <= '0';
         w_start <= '1';
         wait for PERIOD;
         w_start <= '0';

         wait until w_o_done = '1';
         wait for PERIOD;
         
         --assert false
         -- report "Done signal found"
         -- severity note;
         assert w_o_a = x"00000016"
           report "A wasn't set correctly to 22.0 "& to_string(w_o_a)
             &" tb_type_long.vhd."
           severity error;
         assert w_o_b = x"00000018"
           report "B wasn't set correctly to 24.0 "& to_string(w_o_b)
             &" tb_type_long.vhd."
           severity error;

	 --===================================================================
	 --test 4th (and final) condition
         wait for PERIOD*3;
	 
	 w_reset <= '0' ;
         w_start <= '0' ;
         w_i_a <= (others => '0');
         w_i_a_we <= '0';
         w_i_b <= (others => '0');
         w_i_b_we <= '0';
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
         w_i_a <= x"ffffffff";
         w_i_a_we <= '1';
         w_i_b <= x"fffffffb";
         w_i_b_we <= '1';
         wait for PERIOD;
         w_i_a_we <= '0';
         w_i_b_we <= '0';
         w_start <= '1';
         wait for PERIOD;
         w_start <= '0';

         wait until w_o_done = '1';
         wait for PERIOD;
         
         assert w_o_a = x"fffffffc"
           report "A wasn't set correctly to -4.0 "& to_string(w_o_a)
             &" tb_type_long.vhd."
           severity error;
         assert w_o_b = x"fffffffe"
           report "B wasn't set correctly to -2.0 "& to_string(w_o_b)
             &" tb_type_long.vhd."
           severity error;
         assert false
           report "All done signals found"
           severity note;
         wait ;
      end process STIMULI;
end tb_trident_design_arch;




--- Test input
--- @ asim -lib work tb_trident_design
--- @ run 1000ns
--- @ exit





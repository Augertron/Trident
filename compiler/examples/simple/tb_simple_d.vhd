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
--  Generated on Thu Sep 08 17:04:51 MDT 2005


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
         signal o_b : out std_logic_vector(31 downto 0);
         signal i_b : in std_logic_vector(31 downto 0);
         signal i_a_we : in std_logic;
         signal o_done : out std_logic;
         signal i_b_we : in std_logic;
         signal reset : in std_logic;
         signal clk : in std_logic;
         signal i_a : in std_logic_vector(31 downto 0);
         signal o_a : out std_logic_vector(31 downto 0);
         signal start : in std_logic
      );
   end component trident_design;
   constant PERIOD : time := 10 ns;
   signal w_i_a_we : std_logic;
   signal w_i_b_we : std_logic;
   signal w_reset : std_logic;
   signal w_o_done : std_logic;
   signal w_i_a : std_logic_vector(31 downto 0);
   signal w_clk : std_logic := '0';
   signal w_o_b : std_logic_vector(31 downto 0);
   signal w_start : std_logic;
   signal w_o_a : std_logic_vector(31 downto 0);
   signal w_i_b : std_logic_vector(31 downto 0);
-- begin architecture for tb_trident_design
begin
   dut : component trident_design
      port map(
         o_b => w_o_b,
         i_b => w_i_b,
         i_a_we => w_i_a_we,
         o_done => w_o_done,
         i_b_we => w_i_b_we,
         reset => w_reset,
         clk => w_clk,
         i_a => w_i_a,
         o_a => w_o_a,
         start => w_start
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
         w_i_a <= x"c0a00000";
         w_i_a_we <= '1';
         w_i_b <= x"41000000";
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
         assert w_o_a = x"45800000"
           report "A wasn't set correctly to 4096.0 "& to_string(w_o_a)
             &" tb_simple_d.vhd."
           severity error;
         assert w_o_b = x"41000000"
           report "B wasn't set correctly to 8.0 "& to_string(w_o_b)
             &" tb_simple_d.vhd."
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
         w_i_a <= x"40c00000";
         w_i_a_we <= '1';
         w_i_b <= x"c1600000";
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
         assert w_o_a = x"47161000"
           report "A wasn't set correctly to 38416.0 "& to_string(w_o_a)
             &" tb_simple_d.vhd."
           severity error;
         assert w_o_b = x"c1600000"
           report "B wasn't set correctly to -14.0 "& to_string(w_o_b)
             &" tb_simple_d.vhd."
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
         w_i_a <= x"41b00000";
         w_i_a_we <= '1';
         w_i_b <= x"41a80000";
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
         assert w_o_a = x"483dec40"
           report "A wasn't set correctly to 194481.0 "& to_string(w_o_a)
             &" tb_simple_d.vhd."
           severity error;
         assert w_o_b = x"41a80000"
           report "B wasn't set correctly to 21.0 "& to_string(w_o_b)
             &" tb_simple_d.vhd."
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
         w_i_a <= x"bf800000";
         w_i_a_we <= '1';
         w_i_b <= x"c0a00000";
         w_i_b_we <= '1';
         wait for PERIOD;
         w_i_a_we <= '0';
         w_i_b_we <= '0';
         w_start <= '1';
         wait for PERIOD;
         w_start <= '0';

         wait until w_o_done = '1';
         wait for PERIOD;
         
         assert w_o_a = x"441c4000"
           report "A wasn't set correctly to 625.0 "& to_string(w_o_a)
             &" tb_simple_d.vhd."
           severity error;
         assert w_o_b = x"c0a00000"
           report "B wasn't set correctly to -5.0 "& to_string(w_o_b)
             &" tb_simple_d.vhd."
           severity error;
         assert false
           report "All done signals found"
           severity note;
         wait ;
      end process STIMULI;
end tb_trident_design_arch;



--- Test input
--- @ asim -lib work tb_trident_design
--- @ run 2000ns
--- @ exit





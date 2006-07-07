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
--  Generated on Wed Sep 07 18:20:11 MDT 2005


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
         signal i_i : in std_logic_vector(31 downto 0);
         signal i_a : in std_logic_vector(31 downto 0);
         signal reset : in std_logic;
         signal i_i_we : in std_logic;
         signal i_a_we : in std_logic;
         signal o_done : out std_logic;
         signal start : in std_logic;
         signal clk : in std_logic;
         signal o_a : out std_logic_vector(31 downto 0);
         signal o_i : out std_logic_vector(31 downto 0)
      );
   end component trident_design;
   constant PERIOD : time := 10 ns;
   signal w_i_i : std_logic_vector(31 downto 0);
   signal w_o_a : std_logic_vector(31 downto 0);
   signal w_start : std_logic;
   signal w_o_done : std_logic;
   signal w_i_a : std_logic_vector(31 downto 0);
   signal w_clk : std_logic := '0';
   signal w_i_i_we : std_logic;
   signal w_o_i : std_logic_vector(31 downto 0);
   signal w_reset : std_logic;
   signal w_i_a_we : std_logic;
-- begin architecture for tb_trident_design
begin
   dut : component trident_design
      port map(
         i_i => w_i_i,
         i_a => w_i_a,
         reset => w_reset,
         i_i_we => w_i_i_we,
         i_a_we => w_i_a_we,
         o_done => w_o_done,
         start => w_start,
         clk => w_clk,
         o_a => w_o_a,
         o_i => w_o_i
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
         w_i_i <= (others => '0');
         w_i_i_we <= '0';
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
         w_i_a <= x"bf800000";
         w_i_a_we <= '1';
         w_i_i <= x"40a00000";
         w_i_i_we <= '1';
         wait for PERIOD;
         w_i_a_we <= '0';
         w_i_i_we <= '0';
         w_start <= '1';
         wait for PERIOD;
         w_start <= '0';

         wait until w_o_done = '1';
         wait for PERIOD;
         
         --assert false
         --  report "Done signal found"
         --  severity note;
         assert w_o_a = x"bf800000"
           report "A wasn't left as -1 "& to_string(w_o_a)
             &" tb_merge2.vhd."
           severity error;
         assert w_o_i = x"40000000"
           report "I wasn't set correctly to 2 "& to_string(w_o_i)
             &" tb_merge2.vhd."
           severity error;
         
	 --===================================================================
	 --test 2nd (and final) condition
         wait for PERIOD*3;
	 
	 w_reset <= '0' ;
         w_start <= '0' ;
         w_i_a <= (others => '0');
         w_i_a_we <= '0';
         w_i_i <= (others => '0');
         w_i_i_we <= '0';
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
         w_i_a <= x"3f800000";
         w_i_a_we <= '1';
         w_i_i <= x"40a00000";
         w_i_i_we <= '1';
         wait for PERIOD;
         w_i_a_we <= '0';
         w_i_i_we <= '0';
         w_start <= '1';
         wait for PERIOD;
         w_start <= '0';

         wait until w_o_done = '1';
         wait for PERIOD;
         
         assert w_o_a = x"3f800000"
           report "A wasn't left as 1 "& to_string(w_o_a)
             &" tb_merge2.vhd."
           severity error;
         assert w_o_i = x"3f800000"
           report "I wasn't set correctly to 1 "& to_string(w_o_i)
             &" tb_merge2.vhd."
           severity error;
         assert false
           report "All done signals found"
           severity note;
         wait ;
      end process STIMULI;
end tb_trident_design_arch;


--- Test input
--- @ asim -lib work tb_trident_design
--- @ run 600ns
--- @ exit




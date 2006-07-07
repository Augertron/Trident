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
--  Generated on Wed Sep 07 17:25:46 MDT 2005


library ieee;

use ieee.std_logic_unsigned.all;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;


entity tb_trident_design is
end entity tb_trident_design;

architecture tb_trident_design_arch of tb_trident_design is

   component trident_design is
      port(
         signal o_done : out std_logic;
         signal clk : in std_logic;
         signal start : in std_logic;
         signal reset : in std_logic
      );
   end component trident_design;
   constant PERIOD : time := 10 ns;
   signal w_o_done : std_logic;
   signal w_reset : std_logic;
   signal w_start : std_logic;
   signal w_clk : std_logic := '0';
-- begin architecture for tb_trident_design
begin
   --note everything was optimized out! This guy does nothing!!!!!!!!
   dut : component trident_design
      port map(
         o_done => w_o_done,
         clk => w_clk,
         start => w_start,
         reset => w_reset
      );
   w_clk <= not w_clk after PERIOD / 2 ;
   STIMULI:
      process  is
      begin
         w_reset <= '0' ;
         w_start <= '0' ;
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
         w_start <= '1';
         wait for PERIOD;
         w_start <= '0';
         wait until w_o_done = '1';
         wait for PERIOD;
         assert false
           report "Done signal found"
           severity note;
         wait ;
      end process STIMULI;
end tb_trident_design_arch;




--- Test input
--- @ asim -lib work tb_trident_design
--- @ run 400ns
--- @ exit




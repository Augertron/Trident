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
--  Generated on Fri Jun 10 08:42:23 MDT 2005


library ieee, ieee_proposed, std_developerskit;

use ieee.std_logic_unsigned.all;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;

use ieee_proposed.fphdl_base_pkg.all;

use std_developerskit.std_iopak.all;

entity tb_top is
end entity tb_top;

architecture tb_top_arch of tb_top is

   component trident_design is
      port(
         signal o_y1 : out std_logic_vector(31 downto 0);
         signal i_y2 : in std_logic_vector(31 downto 0);
         signal o_x1 : out std_logic_vector(31 downto 0);
         signal i_x2_we : in std_logic;
         signal i_z1 : in std_logic_vector(31 downto 0);
         signal i_y1 : in std_logic_vector(31 downto 0);
         signal i_dist_we : in std_logic;
         signal i_x2 : in std_logic_vector(31 downto 0);
         signal o_x2 : out std_logic_vector(31 downto 0);
         signal i_z2 : in std_logic_vector(31 downto 0);
         signal i_y1_we : in std_logic;
         signal o_z2 : out std_logic_vector(31 downto 0);
         signal o_z1 : out std_logic_vector(31 downto 0);
         signal i_dist : in std_logic_vector(31 downto 0);
         signal i_x1_we : in std_logic;
         signal i_z2_we : in std_logic;
         signal o_y2 : out std_logic_vector(31 downto 0);
         signal i_y2_we : in std_logic;
         signal o_dist : out std_logic_vector(31 downto 0);
         signal start : in std_logic;
         signal clk : in std_logic;
         signal i_x1 : in std_logic_vector(31 downto 0);
         signal i_z1_we : in std_logic;
         signal reset : in std_logic;
         signal o_done : out std_logic
      );
   end component trident_design;
   constant PERIOD : time := 10 ns;
   signal w_o_z1 : std_logic_vector(31 downto 0);
   signal w_i_z2 : std_logic_vector(31 downto 0);
   signal w_i_z1 : std_logic_vector(31 downto 0);
   signal w_o_x1 : std_logic_vector(31 downto 0);
   signal w_clk : std_logic := '0';
   signal w_i_y1 : std_logic_vector(31 downto 0);
   signal w_i_x1_we : std_logic;
   signal w_i_x2_we : std_logic;
   signal w_o_z2 : std_logic_vector(31 downto 0);
   signal w_o_x2 : std_logic_vector(31 downto 0);
   signal w_reset : std_logic;
   signal w_i_y2 : std_logic_vector(31 downto 0);
   signal w_i_y1_we : std_logic;
   signal w_i_z1_we : std_logic;
   signal w_o_done : std_logic;
   signal w_o_y2 : std_logic_vector(31 downto 0);
   signal w_i_dist : std_logic_vector(31 downto 0);
   signal w_i_x2 : std_logic_vector(31 downto 0);
   signal w_i_z2_we : std_logic;
   signal w_i_dist_we : std_logic;
   signal w_start : std_logic;
   signal w_o_y1 : std_logic_vector(31 downto 0);
   signal w_i_y2_we : std_logic;
   signal w_o_dist : std_logic_vector(31 downto 0);
   signal w_i_x1 : std_logic_vector(31 downto 0);
-- begin architecture for tb_top
begin
   dut : component trident_design
      port map(o_y1 => w_o_y1,
         i_y2 => w_i_y2,
         o_x1 => w_o_x1,
         i_x2_we => w_i_x2_we,
         i_z1 => w_i_z1,
         i_y1 => w_i_y1,
         i_dist_we => w_i_dist_we,
         i_x2 => w_i_x2,
         o_x2 => w_o_x2,
         i_z2 => w_i_z2,
         i_y1_we => w_i_y1_we,
         o_z2 => w_o_z2,
         o_z1 => w_o_z1,
         i_dist => w_i_dist,
         i_x1_we => w_i_x1_we,
         i_z2_we => w_i_z2_we,
         o_y2 => w_o_y2,
         i_y2_we => w_i_y2_we,
         o_dist => w_o_dist,
         start => w_start,
         clk => w_clk,
         i_x1 => w_i_x1,
         i_z1_we => w_i_z1_we,
         reset => w_reset,
         o_done => w_o_done
      );
   w_clk <= not w_clk after PERIOD / 2 ;
   STIMULI:
      process  is
      begin
         w_reset <= '0' ;
         w_start <= '0' ;
         w_i_x1 <= (others => '0');
         w_i_x1_we <= '0';
         w_i_x2 <= (others => '0');
         w_i_x2_we <= '0';
         w_i_y1 <= (others => '0');
         w_i_y1_we <= '0';
         w_i_y2 <= (others => '0');
         w_i_y2_we <= '0';
         w_i_z1 <= (others => '0');
         w_i_z1_we <= '0';
         w_i_z2 <= (others => '0');
         w_i_z2_we <= '0';
         w_i_dist <= (others => '0');
         w_i_dist_we <= '0';
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
         w_i_x2 <= to_slv(to_float(2.121320, 8, 23));
         w_i_x2_we <= '1';
         w_i_y2 <= to_slv(to_float(2.121320, 8, 23));
         w_i_y2_we <= '1';
         w_i_z2 <= to_slv(to_float(4.0, 8, 23));
         w_i_z2_we <= '1';
         wait for PERIOD;
         w_i_x2_we <= '0';
         w_i_y2_we <= '0';
         w_i_z2_we <= '0';
         w_start <= '1';
         wait for PERIOD;
         w_start <= '0';

         wait until w_o_done = '1';
         wait for PERIOD;
         
         assert false
           report "Done signal found"
           severity note;
         assert w_o_dist = x"409FFFFF" -- 4.9999999999...
           report "Incorrect dist "& to_string(w_o_dist)
             &" tb_euclid_d.vhd."
           severity error;
         wait;
      End process STIMULI;
   --FIND_DONE:
   --   process  is
   --   begin
   --      wait for PERIOD*12 ;
         --wait until w_o_done = '1';
         --wait for PERIOD;
         
   --      assert w_o_done = '1'
   --        report "Done signal found"
   --        severity note;
  --       wait;
  --    End process FIND_DONE;
end tb_top_arch;

--- Test input
--- @ asim -lib work tb_top
--- @ run 1000ns
--- @ exit



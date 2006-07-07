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
--  Generated on Tue Sep 06 17:46:57 MDT 2005


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
         signal i_f : in std_logic_vector(31 downto 0);
         signal o_f : out std_logic_vector(31 downto 0);
         signal i_b_we : in std_logic;
         signal i_e : in std_logic_vector(31 downto 0);
         signal o_a : out std_logic_vector(31 downto 0);
         signal o_done : out std_logic;
         signal i_d : in std_logic_vector(31 downto 0);
         signal reset : in std_logic;
         signal i_a : in std_logic_vector(31 downto 0);
         signal o_c : out std_logic_vector(31 downto 0);
         signal i_c : in std_logic_vector(31 downto 0);
         signal o_b : out std_logic_vector(31 downto 0);
         signal o_e : out std_logic_vector(31 downto 0);
         signal i_c_we : in std_logic;
         signal i_f_we : in std_logic;
         signal clk : in std_logic;
         signal i_e_we : in std_logic;
         signal i_a_we : in std_logic;
         signal i_d_we : in std_logic;
         signal o_d : out std_logic_vector(31 downto 0);
         signal i_b : in std_logic_vector(31 downto 0);
         signal start : in std_logic
      );
   end component trident_design;
   constant PERIOD : time := 10 ns;
   signal w_i_c : std_logic_vector(31 downto 0);
   signal w_o_d : std_logic_vector(31 downto 0);
   signal w_o_done : std_logic;
   signal w_o_b : std_logic_vector(31 downto 0);
   signal w_o_a : std_logic_vector(31 downto 0);
   signal w_i_a_we : std_logic;
   signal w_i_e : std_logic_vector(31 downto 0);
   signal w_i_d : std_logic_vector(31 downto 0);
   signal w_reset : std_logic;
   signal w_o_f : std_logic_vector(31 downto 0);
   signal w_start : std_logic;
   signal w_i_b : std_logic_vector(31 downto 0);
   signal w_clk : std_logic := '0';
   signal w_i_f_we : std_logic;
   signal w_o_e : std_logic_vector(31 downto 0);
   signal w_i_a : std_logic_vector(31 downto 0);
   signal w_i_d_we : std_logic;
   signal w_i_e_we : std_logic;
   signal w_i_f : std_logic_vector(31 downto 0);
   signal w_i_c_we : std_logic;
   signal w_i_b_we : std_logic;
   signal w_o_c : std_logic_vector(31 downto 0);
-- begin architecture for tb_trident_design
begin
   dut : component trident_design
      port map(
         i_f => w_i_f,
         o_f => w_o_f,
         i_b_we => w_i_b_we,
         i_e => w_i_e,
         o_a => w_o_a,
         o_done => w_o_done,
         i_d => w_i_d,
         reset => w_reset,
         i_a => w_i_a,
         o_c => w_o_c,
         i_c => w_i_c,
         o_b => w_o_b,
         o_e => w_o_e,
         i_c_we => w_i_c_we,
         i_f_we => w_i_f_we,
         clk => w_clk,
         i_e_we => w_i_e_we,
         i_a_we => w_i_a_we,
         i_d_we => w_i_d_we,
         o_d => w_o_d,
         i_b => w_i_b,
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
         w_i_c <= (others => '0');
         w_i_c_we <= '0';
         w_i_d <= (others => '0');
         w_i_d_we <= '0';
         w_i_e <= (others => '0');
         w_i_e_we <= '0';
         w_i_f <= (others => '0');
         w_i_f_we <= '0';
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
         w_i_a <= x"0000000A";
         w_i_a_we <= '1';
         w_i_b <= x"0000000B";
         w_i_b_we <= '1';
         w_i_c <= x"0000000C";
         w_i_c_we <= '1';
         w_i_d <= x"0000000D";
         w_i_d_we <= '1';
         w_i_e <= x"0000000E";
         w_i_e_we <= '1';
         w_i_f <= x"0000000F";
         w_i_f_we <= '1';
         wait for PERIOD;
         w_i_a_we <= '0';
         w_i_b_we <= '0';
         w_i_c_we <= '0';
         w_i_d_we <= '0';
         w_i_e_we <= '0';
         w_i_f_we <= '0';
         w_start <= '1';
         wait for PERIOD;
         w_start <= '0';

         wait until w_o_done = '1';
         wait for PERIOD;
         
         --assert false
         --  report "Done signal found"
         --  severity note;
         assert w_o_f = w_o_e
           report "F wasn't set correctly to E "& to_string(w_o_f)
             &" (E="& to_string(w_o_e)
             &") tb_ifor_b.vhd."
           severity error;
         
	 --===================================================================
	 --test 2nd (and final) condition
         wait for PERIOD*3;
	 
	 w_reset <= '0' ;
         w_start <= '0' ;
         w_i_a <= (others => '0');
         w_i_a_we <= '0';
         w_i_b <= (others => '0');
         w_i_b_we <= '0';
         w_i_c <= (others => '0');
         w_i_c_we <= '0';
         w_i_d <= (others => '0');
         w_i_d_we <= '0';
         w_i_e <= (others => '0');
         w_i_e_we <= '0';
         w_i_f <= (others => '0');
         w_i_f_we <= '0';
        wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
         w_i_a <= x"00000000";
         w_i_a_we <= '1';
         w_i_b <= x"00000000";
         w_i_b_we <= '1';
         w_i_c <= x"00000000";
         w_i_c_we <= '1';
         w_i_d <= x"00000000";
         w_i_d_we <= '1';
         w_i_e <= x"0000000E";
         w_i_e_we <= '1';
         w_i_f <= x"0000000F";
         w_i_f_we <= '1';
         wait for PERIOD;
         w_i_a_we <= '0';
         w_i_b_we <= '0';
         w_i_c_we <= '0';
         w_i_d_we <= '0';
         w_i_e_we <= '0';
         w_i_f_we <= '0';
         w_start <= '1';
         wait for PERIOD;
         w_start <= '0';

         wait until w_o_done = '1';
         wait for PERIOD;
         
         assert w_o_f /= w_o_e
           report "F was set to E when it shouldn't have been "
	     & to_string(w_o_f)
             &" (E="& to_string(w_o_e)
             &") tb_ifor_b.vhd."
           severity error;
         assert false
           report "Done signal found"
           severity note;
         wait ;
      end process STIMULI;
end tb_trident_design_arch;



--- Test input
--- @ asim -lib work tb_trident_design
--- @ run 1000ns
--- @ exit





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
--  Generated on Wed Sep 07 14:12:53 MDT 2005


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
         signal o_n : out std_logic_vector(31 downto 0);
         signal clk : in std_logic;
         signal i_f_we : in std_logic;
         signal o_done : out std_logic;
         signal i_k : in std_logic_vector(31 downto 0);
         signal o_g : out std_logic_vector(31 downto 0);
         signal i_m_we : in std_logic;
         signal i_p : in std_logic_vector(31 downto 0);
         signal i_j_we : in std_logic;
         signal o_l : out std_logic_vector(31 downto 0);
         signal start : in std_logic;
         signal i_f : in std_logic_vector(31 downto 0);
         signal o_r : out std_logic_vector(31 downto 0);
         signal i_b : in std_logic_vector(31 downto 0);
         signal i_g : in std_logic_vector(31 downto 0);
         signal i_c_we : in std_logic;
         signal i_d : in std_logic_vector(31 downto 0);
         signal o_b : out std_logic_vector(31 downto 0);
         signal i_p_we : in std_logic;
         signal i_n_we : in std_logic;
         signal i_a_we : in std_logic;
         signal o_p : out std_logic_vector(31 downto 0);
         signal o_i : out std_logic_vector(31 downto 0);
         signal i_l_we : in std_logic;
         signal o_h : out std_logic_vector(31 downto 0);
         signal o_d : out std_logic_vector(31 downto 0);
         signal i_o : in std_logic_vector(31 downto 0);
         signal i_n : in std_logic_vector(31 downto 0);
         signal i_a : in std_logic_vector(31 downto 0);
         signal o_e : out std_logic_vector(31 downto 0);
         signal o_j : out std_logic_vector(31 downto 0);
         signal i_j : in std_logic_vector(31 downto 0);
         signal o_c : out std_logic_vector(31 downto 0);
         signal o_k : out std_logic_vector(31 downto 0);
         signal i_d_we : in std_logic;
         signal i_i_we : in std_logic;
         signal i_l : in std_logic_vector(31 downto 0);
         signal o_o : out std_logic_vector(31 downto 0);
         signal o_m : out std_logic_vector(31 downto 0);
         signal reset : in std_logic;
         signal i_m : in std_logic_vector(31 downto 0);
         signal i_e_we : in std_logic;
         signal i_r : in std_logic_vector(31 downto 0);
         signal o_f : out std_logic_vector(31 downto 0);
         signal i_b_we : in std_logic;
         signal i_e : in std_logic_vector(31 downto 0);
         signal i_o_we : in std_logic;
         signal i_k_we : in std_logic;
         signal i_i : in std_logic_vector(31 downto 0);
         signal i_c : in std_logic_vector(31 downto 0);
         signal i_h_we : in std_logic;
         signal o_a : out std_logic_vector(31 downto 0);
         signal i_r_we : in std_logic;
         signal i_g_we : in std_logic;
         signal i_h : in std_logic_vector(31 downto 0)
      );
   end component trident_design;
   constant PERIOD : time := 10 ns;
   signal w_i_a : std_logic_vector(31 downto 0);
   signal w_i_g_we : std_logic;
   signal w_i_h_we : std_logic;
   signal w_i_m_we : std_logic;
   signal w_i_e : std_logic_vector(31 downto 0);
   signal w_o_d : std_logic_vector(31 downto 0);
   signal w_o_e : std_logic_vector(31 downto 0);
   signal w_start : std_logic;
   signal w_i_r : std_logic_vector(31 downto 0);
   signal w_i_n : std_logic_vector(31 downto 0);
   signal w_i_b : std_logic_vector(31 downto 0);
   signal w_i_d : std_logic_vector(31 downto 0);
   signal w_i_i_we : std_logic;
   signal w_i_m : std_logic_vector(31 downto 0);
   signal w_i_g : std_logic_vector(31 downto 0);
   signal w_i_f_we : std_logic;
   signal w_o_g : std_logic_vector(31 downto 0);
   signal w_o_l : std_logic_vector(31 downto 0);
   signal w_i_o_we : std_logic;
   signal w_o_k : std_logic_vector(31 downto 0);
   signal w_i_a_we : std_logic;
   signal w_clk : std_logic := '0';
   signal w_i_d_we : std_logic;
   signal w_i_p_we : std_logic;
   signal w_o_a : std_logic_vector(31 downto 0);
   signal w_o_f : std_logic_vector(31 downto 0);
   signal w_i_k_we : std_logic;
   signal w_i_f : std_logic_vector(31 downto 0);
   signal w_o_m : std_logic_vector(31 downto 0);
   signal w_o_r : std_logic_vector(31 downto 0);
   signal w_reset : std_logic;
   signal w_i_h : std_logic_vector(31 downto 0);
   signal w_i_b_we : std_logic;
   signal w_i_l : std_logic_vector(31 downto 0);
   signal w_i_k : std_logic_vector(31 downto 0);
   signal w_i_o : std_logic_vector(31 downto 0);
   signal w_o_n : std_logic_vector(31 downto 0);
   signal w_i_c : std_logic_vector(31 downto 0);
   signal w_i_j : std_logic_vector(31 downto 0);
   signal w_i_l_we : std_logic;
   signal w_i_c_we : std_logic;
   signal w_o_h : std_logic_vector(31 downto 0);
   signal w_o_i : std_logic_vector(31 downto 0);
   signal w_o_j : std_logic_vector(31 downto 0);
   signal w_o_p : std_logic_vector(31 downto 0);
   signal w_i_n_we : std_logic;
   signal w_o_b : std_logic_vector(31 downto 0);
   signal w_i_e_we : std_logic;
   signal w_i_p : std_logic_vector(31 downto 0);
   signal w_i_r_we : std_logic;
   signal w_o_c : std_logic_vector(31 downto 0);
   signal w_o_done : std_logic;
   signal w_i_j_we : std_logic;
   signal w_o_o : std_logic_vector(31 downto 0);
   signal w_i_i : std_logic_vector(31 downto 0);
-- begin architecture for tb_trident_design
begin
   dut : component trident_design
      port map(
         o_n => w_o_n,
         clk => w_clk,
         i_f_we => w_i_f_we,
         o_done => w_o_done,
         i_k => w_i_k,
         o_g => w_o_g,
         i_m_we => w_i_m_we,
         i_p => w_i_p,
         i_j_we => w_i_j_we,
         o_l => w_o_l,
         start => w_start,
         i_f => w_i_f,
         o_r => w_o_r,
         i_b => w_i_b,
         i_g => w_i_g,
         i_c_we => w_i_c_we,
         i_d => w_i_d,
         o_b => w_o_b,
         i_p_we => w_i_p_we,
         i_n_we => w_i_n_we,
         i_a_we => w_i_a_we,
         o_p => w_o_p,
         o_i => w_o_i,
         i_l_we => w_i_l_we,
         o_h => w_o_h,
         o_d => w_o_d,
         i_o => w_i_o,
         i_n => w_i_n,
         i_a => w_i_a,
         o_e => w_o_e,
         o_j => w_o_j,
         i_j => w_i_j,
         o_c => w_o_c,
         o_k => w_o_k,
         i_d_we => w_i_d_we,
         i_i_we => w_i_i_we,
         i_l => w_i_l,
         o_o => w_o_o,
         o_m => w_o_m,
         reset => w_reset,
         i_m => w_i_m,
         i_e_we => w_i_e_we,
         i_r => w_i_r,
         o_f => w_o_f,
         i_b_we => w_i_b_we,
         i_e => w_i_e,
         i_o_we => w_i_o_we,
         i_k_we => w_i_k_we,
         i_i => w_i_i,
         i_c => w_i_c,
         i_h_we => w_i_h_we,
         o_a => w_o_a,
         i_r_we => w_i_r_we,
         i_g_we => w_i_g_we,
         i_h => w_i_h
      );
   w_clk <= not w_clk after PERIOD / 2 ;
   STIMULI:
      process  is
      begin
	 
	 --1st and only test condition
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
         w_i_g <= (others => '0');
         w_i_g_we <= '0';
         w_i_h <= (others => '0');
         w_i_h_we <= '0';
         w_i_i <= (others => '0');
         w_i_i_we <= '0';
         w_i_j <= (others => '0');
         w_i_j_we <= '0';
         w_i_k <= (others => '0');
         w_i_k_we <= '0';
         w_i_l <= (others => '0');
         w_i_l_we <= '0';
         w_i_m <= (others => '0');
         w_i_m_we <= '0';
         w_i_n <= (others => '0');
         w_i_n_we <= '0';
         w_i_o <= (others => '0');
         w_i_o_we <= '0';
         w_i_p <= (others => '0');
         w_i_p_we <= '0';
         w_i_r <= (others => '0');
         w_i_r_we <= '0';
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
	 w_i_a <= x"40a00000";          -- 5.0
	 w_i_a_we <= '0';
	 w_i_b <= x"41200000";          -- 10.0
	 w_i_b_we <= '0';
	 w_i_c <= x"42180000";          -- 38.0
	 w_i_c_we <= '0';
	 w_i_d <= x"40000000";          -- 2.0
	 w_i_d_we <= '0';
	 w_i_e <= x"40e00000";          -- 7.0
	 w_i_e_we <= '0';
	 w_i_f <= x"40800000";          -- 4.0
	 w_i_f_we <= '0';
	 w_i_g <= x"42820000";          -- 65.0
	 w_i_g_we <= '0';
	 w_i_h <= x"40800000";          -- 4.0
	 w_i_h_we <= '0';
	 w_i_i <= x"41c80000";          -- 25.0
	 w_i_i_we <= '0';
	 w_i_j <= x"40e00000";          -- 7.0
	 w_i_j_we <= '0';
	 w_i_k <= x"41400000";          -- 12.0
	 w_i_k_we <= '0';
	 w_i_l <= x"40a00000";          -- 5.0
	 w_i_l_we <= '0';
	 w_i_m <= x"40e00000";          -- 7.0
	 w_i_m_we <= '0';
	 w_i_n <= x"40400000";          -- 3.0
	 w_i_n_we <= '0';
	 w_i_o <= x"40000000";          -- 2.0
	 w_i_o_we <= '0';
	 w_i_p <= x"42940000";          -- 74.0
	 w_i_p_we <= '0';
	 w_i_r <= x"41c80000";          -- 25.0
	 w_i_r_we <= '0';
	 wait for PERIOD;
	 w_i_a_we <= '1';
	 w_i_b_we <= '1';
	 w_i_c_we <= '1';
	 w_i_d_we <= '1';
	 w_i_e_we <= '1';
	 w_i_f_we <= '1';
	 w_i_g_we <= '1';
	 w_i_h_we <= '1';
	 w_i_i_we <= '1';
	 w_i_j_we <= '1';
	 w_i_k_we <= '1';
	 w_i_l_we <= '1';
	 w_i_m_we <= '1';
	 w_i_n_we <= '1';
	 w_i_o_we <= '1';
	 w_i_p_we <= '1';
	 w_i_r_we <= '1';
	 wait for PERIOD;
	 w_i_a_we <= '0';
	 w_i_b_we <= '0';
	 w_i_c_we <= '0';
	 w_i_d_we <= '0';
	 w_i_e_we <= '0';
	 w_i_f_we <= '0';
	 w_i_g_we <= '0';
	 w_i_h_we <= '0';
	 w_i_i_we <= '0';
	 w_i_j_we <= '0';
	 w_i_k_we <= '0';
	 w_i_l_we <= '0';
	 w_i_m_we <= '0';
	 w_i_n_we <= '0';
	 w_i_o_we <= '0';
	 w_i_p_we <= '0';
	 w_i_r_we <= '0';
	 wait for PERIOD;

	 w_start <= '1';
	 wait for PERIOD;
	 w_start <= '0';
         wait until w_o_done = '1';
         wait for PERIOD;
         
         assert w_o_a = x"450de000" -- 2270.0...
           report "A wasn't set correctly to 2270.0 "& to_string(w_o_a)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_b = x"450b8000" -- 2232.0...
           report "B wasn't set correctly to 2232.0 "& to_string(w_o_b)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_c = x"42180000" -- 38.0...
           report "C wasn't set correctly to 38.0 "& to_string(w_o_c)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_d = x"41300000" -- 11.0...
           report "D wasn't set correctly to 11.0 "& to_string(w_o_d)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_e = x"40e00000" -- 7.0...
           report "E wasn't set correctly to 7.0 "& to_string(w_o_e)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_f = x"40800000" -- 4.0...
           report "F wasn't set correctly to 4.0 "& to_string(w_o_f)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_g = x"450e9000" -- 2281.0...
           report "G wasn't set correctly to 2281.0 "& to_string(w_o_g)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_h = x"42000000" -- 32.0...
           report "H wasn't set correctly to 32.0 "& to_string(w_o_h)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_i = x"41c80000" -- 25.0...
           report "I wasn't set correctly to 25.0 "& to_string(w_o_i)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_j = x"40e00000" -- 7.0...
           report "J wasn't set correctly to 7.0 "& to_string(w_o_j)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_k = x"41400000" -- 12.0...
           report "K wasn't set correctly to 12.0 "& to_string(w_o_k)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_l = x"40a00000" -- 5.0...
           report "L wasn't set correctly to 5.0 "& to_string(w_o_l)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_m = x"40e00000" -- 7.0...
           report "M wasn't set correctly to 7.0 "& to_string(w_o_m)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_n = x"42300000" -- 44.0...
           report "N wasn't set correctly to 44.0 "& to_string(w_o_n)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_o = x"45115000" -- 2325.0...
           report "O wasn't set correctly to 2325.0 "& to_string(w_o_o)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_p = x"45115000" -- 2325.0...
           report "P wasn't set correctly to 2325.0 "& to_string(w_o_p)
             &" tb_complicatedloop.vhd."
           severity error;
         assert w_o_r = x"41c80000" -- 25.0...
           report "R wasn't set correctly to 25.0 "& to_string(w_o_r)
             &" tb_complicatedloop.vhd."
           severity error;
         assert false
           report "Done signal found"
           severity note;
         wait ;
      end process STIMULI;
end tb_trident_design_arch;



--- Test input
--- @ asim -lib work tb_trident_design
--- @ run 20000ns
--- @ exit





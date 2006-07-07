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
--  Generated on Mon May 16 16:49:37 MDT 2005


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
         signal i_y2l : in std_logic_vector(31 downto 0);
         signal o_y2l : out std_logic_vector(31 downto 0);
         signal start : in std_logic;
         signal i_y1l_we : in std_logic;
         signal i_ssq : in std_logic_vector(31 downto 0);
         signal i_rhsl_we : in std_logic;
         signal i_x1l_we : in std_logic;
         signal o_li : out std_logic_vector(31 downto 0);
         signal i_l_we : in std_logic;
         signal i_x2l_we : in std_logic;
         signal o_delyl : out std_logic_vector(31 downto 0);
         signal i_rhse : in std_logic_vector(31 downto 0);
         signal i_li_we : in std_logic;
         signal i_ey : in std_logic_vector(31 downto 0);
         signal o_rhsl : out std_logic_vector(31 downto 0);
         signal i_delyl : in std_logic_vector(31 downto 0);
         signal o_delxl : out std_logic_vector(31 downto 0);
         signal i_rhsl : in std_logic_vector(31 downto 0);
         signal o_sqlnl : out std_logic_vector(31 downto 0);
         signal o_l : out std_logic_vector(31 downto 0);
         signal o_rhse : out std_logic_vector(31 downto 0);
         signal o_y1l : out std_logic_vector(31 downto 0);
         signal i_sqlnl : in std_logic_vector(31 downto 0);
         signal o_ssq : out std_logic_vector(31 downto 0);
         signal i_delxl_we : in std_logic;
         signal i_ex_we : in std_logic;
         signal i_y1l : in std_logic_vector(31 downto 0);
         signal reset : in std_logic;
         signal o_x2l : out std_logic_vector(31 downto 0);
         signal o_le : out std_logic_vector(31 downto 0);
         signal i_ey_we : in std_logic;
         signal i_delyl_we : in std_logic;
         signal i_ex : in std_logic_vector(31 downto 0);
         signal i_x1l : in std_logic_vector(31 downto 0);
         signal i_y2l_we : in std_logic;
         signal i_li : in std_logic_vector(31 downto 0);
         signal o_ex : out std_logic_vector(31 downto 0);
         signal o_done : out std_logic;
         signal clk : in std_logic;
         signal i_le_we : in std_logic;
         signal o_ey : out std_logic_vector(31 downto 0);
         signal i_x2l : in std_logic_vector(31 downto 0);
         signal i_delxl : in std_logic_vector(31 downto 0);
         signal i_l : in std_logic_vector(31 downto 0);
         signal o_x1l : out std_logic_vector(31 downto 0);
         signal i_sqlnl_we : in std_logic;
         signal i_ssq_we : in std_logic;
         signal i_le : in std_logic_vector(31 downto 0);
         signal i_rhse_we : in std_logic
      );
   end component trident_design;
   constant PERIOD : time := 10 ns;
   signal w_i_x1l_we : std_logic;
   signal w_i_ex : std_logic_vector(31 downto 0);
   signal w_reset : std_logic;
   signal w_i_delxl : std_logic_vector(31 downto 0);
   signal w_i_ssq : std_logic_vector(31 downto 0);
   signal w_i_li : std_logic_vector(31 downto 0);
   signal w_i_ey : std_logic_vector(31 downto 0);
   signal w_i_y1l_we : std_logic;
   signal w_i_ex_we : std_logic;
   signal w_i_le : std_logic_vector(31 downto 0);
   signal w_o_ey : std_logic_vector(31 downto 0);
   signal w_o_sqlnl : std_logic_vector(31 downto 0);
   signal w_i_le_we : std_logic;
   signal w_i_x2l_we : std_logic;
   signal w_i_rhsl : std_logic_vector(31 downto 0);
   signal w_i_l_we : std_logic;
   signal w_i_rhse : std_logic_vector(31 downto 0);
   signal w_i_y2l_we : std_logic;
   signal w_o_ex : std_logic_vector(31 downto 0);
   signal w_i_delxl_we : std_logic;
   signal w_i_x2l : std_logic_vector(31 downto 0);
   signal w_i_sqlnl : std_logic_vector(31 downto 0);
   signal w_i_ey_we : std_logic;
   signal w_o_rhse : std_logic_vector(31 downto 0);
   signal w_o_y2l : std_logic_vector(31 downto 0);
   signal w_i_y1l : std_logic_vector(31 downto 0);
   signal w_i_rhsl_we : std_logic;
   signal w_i_rhse_we : std_logic;
   signal w_o_rhsl : std_logic_vector(31 downto 0);
   signal w_o_y1l : std_logic_vector(31 downto 0);
   signal w_o_done : std_logic;
   signal w_o_ssq : std_logic_vector(31 downto 0);
   signal w_i_delyl : std_logic_vector(31 downto 0);
   signal w_i_x1l : std_logic_vector(31 downto 0);
   signal w_i_l : std_logic_vector(31 downto 0);
   signal w_o_le : std_logic_vector(31 downto 0);
   signal w_o_delyl : std_logic_vector(31 downto 0);
   signal w_o_x1l : std_logic_vector(31 downto 0);
   signal w_i_sqlnl_we : std_logic;
   signal w_i_ssq_we : std_logic;
   signal w_i_li_we : std_logic;
   signal w_o_x2l : std_logic_vector(31 downto 0);
   signal w_o_li : std_logic_vector(31 downto 0);
   signal w_i_y2l : std_logic_vector(31 downto 0);
   signal w_i_delyl_we : std_logic;
   signal w_o_delxl : std_logic_vector(31 downto 0);
   signal w_start : std_logic;
   signal w_o_l : std_logic_vector(31 downto 0);
   signal w_clk : std_logic := '0';
-- begin architecture for tb_trident_design
   
-- begin architecture for tb_top
begin
   dut : component trident_design
      port map(
         i_y2l => w_i_y2l,
         o_y2l => w_o_y2l,
         start => w_start,
         i_y1l_we => w_i_y1l_we,
         i_ssq => w_i_ssq,
         i_rhsl_we => w_i_rhsl_we,
         i_x1l_we => w_i_x1l_we,
         o_li => w_o_li,
         i_l_we => w_i_l_we,
         i_x2l_we => w_i_x2l_we,
         o_delyl => w_o_delyl,
         i_rhse => w_i_rhse,
         i_li_we => w_i_li_we,
         i_ey => w_i_ey,
         o_rhsl => w_o_rhsl,
         i_delyl => w_i_delyl,
         o_delxl => w_o_delxl,
         i_rhsl => w_i_rhsl,
         o_sqlnl => w_o_sqlnl,
         o_l => w_o_l,
         o_rhse => w_o_rhse,
         o_y1l => w_o_y1l,
         i_sqlnl => w_i_sqlnl,
         o_ssq => w_o_ssq,
         i_delxl_we => w_i_delxl_we,
         i_ex_we => w_i_ex_we,
         i_y1l => w_i_y1l,
         reset => w_reset,
         o_x2l => w_o_x2l,
         o_le => w_o_le,
         i_ey_we => w_i_ey_we,
         i_delyl_we => w_i_delyl_we,
         i_ex => w_i_ex,
         i_x1l => w_i_x1l,
         i_y2l_we => w_i_y2l_we,
         i_li => w_i_li,
         o_ex => w_o_ex,
         o_done => w_o_done,
         clk => w_clk,
         i_le_we => w_i_le_we,
         o_ey => w_o_ey,
         i_x2l => w_i_x2l,
         i_delxl => w_i_delxl,
         i_l => w_i_l,
         o_x1l => w_o_x1l,
         i_sqlnl_we => w_i_sqlnl_we,
         i_ssq_we => w_i_ssq_we,
         i_le => w_i_le,
         i_rhse_we => w_i_rhse_we
      );
   w_clk <= not w_clk after PERIOD / 2 ;
   STIMULI:
      process  is
      begin
         --test 1st condition:
	 
	 w_reset <= '0' ;
         w_start <= '0' ;
	 w_i_delxl <= (others => '0');
	 w_i_delxl_we <= '0';
	 w_i_delyl <= (others => '0');
	 w_i_delyl_we <= '0';
	 w_i_ex <= (others => '0');
	 w_i_ex_we <= '0';
	 w_i_ey <= (others => '0');
	 w_i_ey_we <= '0';
	 w_i_rhse <= (others => '0');
	 w_i_rhse_we <= '0';
	 w_i_rhsl <= (others => '0');
	 w_i_rhsl_we <= '0';
	 w_i_x1l <= (others => '0');
	 w_i_x1l_we <= '0';
	 w_i_y1l <= (others => '0');
	 w_i_y1l_we <= '0';
	 w_i_x2l <= (others => '0');
	 w_i_x2l_we <= '0';
	 w_i_y2l <= (others => '0');
	 w_i_y2l_we <= '0';
	 w_i_sqlnl <= (others => '0');
	 w_i_sqlnl_we <= '0';
	 w_i_ssq <= (others => '0');
	 w_i_ssq_we <= '0';
	 w_i_l <= (others => '0');
	 w_i_l_we <= '0';
	 w_i_le <= (others => '0');
	 w_i_le_we <= '0';
	 w_i_li <= (others => '0');
	 w_i_li_we <= '0';
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
	 w_i_delxl <= x"BD2F0E80";
	 w_i_delyl <= x"BE28140D";
	 w_i_ex <= x"BF7EE0EC";
	 w_i_ey <= x"3DBF7B99";
	 w_i_rhse <= x"BDBF72B5";
	 w_i_rhsl <= x"3E2D0E8E";
	 w_i_x1l <= x"3F7C5153";
	 w_i_y1l <= x"BE2D0E8D";
	 w_i_x2l <= x"3F71606B";
	 w_i_y2l <= x"BEAA914D";
	 w_i_sqlnl <= x"3CEBAB40";
	 w_i_ssq <= x"00000000";
	 w_i_l <= x"0000000A";
	 w_i_le <= x"0000000B";
	 w_i_li <= x"0000000C";

         wait for PERIOD;
	 w_i_delxl_we <='1';
	 w_i_delyl_we <='1';
	 w_i_ex_we <='1';
	 w_i_ey_we <='1';
	 w_i_rhse_we <='1';
	 w_i_rhsl_we <='1';
	 w_i_x1l_we <='1';
	 w_i_y1l_we <='1';
	 w_i_x2l_we <='1';
	 w_i_y2l_we <='1';
	 w_i_sqlnl_we <='1';
	 w_i_ssq_we <='1';
	 w_i_l_we <='1';
	 w_i_le_we <='1';
	 w_i_li_we <='1';
	 wait for PERIOD;
	 w_i_delxl_we <='0';
	 w_i_delyl_we <='0';
	 w_i_ex_we <='0';
	 w_i_ey_we <='0';
	 w_i_rhse_we <='0';
	 w_i_rhsl_we <='0';
	 w_i_x1l_we <='0';
	 w_i_y1l_we <='0';
	 w_i_x2l_we <='0';
	 w_i_y2l_we <='0';
	 w_i_sqlnl_we <='0';
	 w_i_ssq_we <='0';
	 w_i_l_we <='0';
	 w_i_le_we <='0';
	 w_i_li_we <='0';
	 wait for PERIOD;

	 w_start <= '1';
	 wait for PERIOD;
	 w_start <= '0';
         wait until w_o_done = '1';
         wait for PERIOD;
         
         --assert false
         --  report "Done signal found"
        --   severity note;
         assert w_o_ssq = x"3E1598C0"
           report "ssq wasn't set correctly to x3E1598C0 "& to_string(w_o_ssq)
             &" tb_photon_variable.vhd."
           severity error;
         
	 --===================================================================
	 --test 2nd condition
         wait for PERIOD*3;
	 
	 w_reset <= '0' ;
         w_start <= '0' ;
	 w_i_delxl <= (others => '0');
	 w_i_delxl_we <= '0';
	 w_i_delyl <= (others => '0');
	 w_i_delyl_we <= '0';
	 w_i_ex <= (others => '0');
	 w_i_ex_we <= '0';
	 w_i_ey <= (others => '0');
	 w_i_ey_we <= '0';
	 w_i_rhse <= (others => '0');
	 w_i_rhse_we <= '0';
	 w_i_rhsl <= (others => '0');
	 w_i_rhsl_we <= '0';
	 w_i_x1l <= (others => '0');
	 w_i_x1l_we <= '0';
	 w_i_y1l <= (others => '0');
	 w_i_y1l_we <= '0';
	 w_i_x2l <= (others => '0');
	 w_i_x2l_we <= '0';
	 w_i_y2l <= (others => '0');
	 w_i_y2l_we <= '0';
	 w_i_sqlnl <= (others => '0');
	 w_i_sqlnl_we <= '0';
	 w_i_ssq <= (others => '0');
	 w_i_ssq_we <= '0';
	 w_i_l <= (others => '0');
	 w_i_l_we <= '0';
	 w_i_le <= (others => '0');
	 w_i_le_we <= '0';
	 w_i_li <= (others => '0');
	 w_i_li_we <= '0';
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
	 w_i_delxl <= x"BD8F1488";
	 w_i_delyl <= x"BE1E43B4";
	 w_i_ex <= x"BF7EE0EC";
	 w_i_ey <= x"3DBF7B99";
	 w_i_rhse <= x"BDBF72B5";
	 w_i_rhsl <= x"3E2D0E8E";
	 w_i_x1l <= x"3F71606B";
	 w_i_y1l <= x"BEAA914D";
	 w_i_x2l <= x"3F5F7DDA";
	 w_i_y2l <= x"BEF9B327";
	 w_i_sqlnl <= x"3CEBAB41";
	 w_i_ssq <= x"00000000";
	 w_i_l <= x"0000000A";
	 w_i_le <= x"0000000B";
	 w_i_li <= x"0000000C";

         wait for PERIOD;
	 w_i_delxl_we <='1';
	 w_i_delyl_we <='1';
	 w_i_ex_we <='1';
	 w_i_ey_we <='1';
	 w_i_rhse_we <='1';
	 w_i_rhsl_we <='1';
	 w_i_x1l_we <='1';
	 w_i_y1l_we <='1';
	 w_i_x2l_we <='1';
	 w_i_y2l_we <='1';
	 w_i_sqlnl_we <='1';
	 w_i_ssq_we <='1';
	 w_i_l_we <='1';
	 w_i_le_we <='1';
	 w_i_li_we <='1';
	 wait for PERIOD;
	 w_i_delxl_we <='0';
	 w_i_delyl_we <='0';
	 w_i_ex_we <='0';
	 w_i_ey_we <='0';
	 w_i_rhse_we <='0';
	 w_i_rhsl_we <='0';
	 w_i_x1l_we <='0';
	 w_i_y1l_we <='0';
	 w_i_x2l_we <='0';
	 w_i_y2l_we <='0';
	 w_i_sqlnl_we <='0';
	 w_i_ssq_we <='0';
	 w_i_l_we <='0';
	 w_i_le_we <='0';
	 w_i_li_we <='0';
	 wait for PERIOD;

	 w_start <= '1';
	 wait for PERIOD;
	 w_start <= '0';
         wait until w_o_done = '1';
         wait for PERIOD;
         
         --assert false
         --  report "Done signal found"
        --   severity note;
         assert w_o_ssq = x"3ECEA43B"
           report "ssq wasn't set correctly to x3ECEA43B "& to_string(w_o_ssq)
             &" tb_photon_variable.vhd."
           severity error;
         
	 --===================================================================
	 --test 3rd condition
         wait for PERIOD*3;
	 
	 w_reset <= '0' ;
         w_start <= '0' ;
	 w_i_delxl <= (others => '0');
	 w_i_delxl_we <= '0';
	 w_i_delyl <= (others => '0');
	 w_i_delyl_we <= '0';
	 w_i_ex <= (others => '0');
	 w_i_ex_we <= '0';
	 w_i_ey <= (others => '0');
	 w_i_ey_we <= '0';
	 w_i_rhse <= (others => '0');
	 w_i_rhse_we <= '0';
	 w_i_rhsl <= (others => '0');
	 w_i_rhsl_we <= '0';
	 w_i_x1l <= (others => '0');
	 w_i_x1l_we <= '0';
	 w_i_y1l <= (others => '0');
	 w_i_y1l_we <= '0';
	 w_i_x2l <= (others => '0');
	 w_i_x2l_we <= '0';
	 w_i_y2l <= (others => '0');
	 w_i_y2l_we <= '0';
	 w_i_sqlnl <= (others => '0');
	 w_i_sqlnl_we <= '0';
	 w_i_ssq <= (others => '0');
	 w_i_ssq_we <= '0';
	 w_i_l <= (others => '0');
	 w_i_l_we <= '0';
	 w_i_le <= (others => '0');
	 w_i_le_we <= '0';
	 w_i_li <= (others => '0');
	 w_i_li_we <= '0';
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
	 w_i_delxl <= x"BDC28400";
	 w_i_delyl <= x"BE0FE5CE";
	 w_i_ex <= x"BF7EE0EC";
	 w_i_ey <= x"3DBF7B99";
	 w_i_rhse <= x"BDBF72B5";
	 w_i_rhsl <= x"3E2D0E8D";
	 w_i_x1l <= x"3F5F7DDA";
	 w_i_y1l <= x"BEF9B327";
	 w_i_x2l <= x"3F472D5A";
	 w_i_y2l <= x"BF20D307";
	 w_i_sqlnl <= x"3CEBAB3F";
	 w_i_ssq <= x"00000000";
	 w_i_l <= x"0000000A";
	 w_i_le <= x"0000000B";
	 w_i_li <= x"0000000C";

         wait for PERIOD;
	 w_i_delxl_we <='1';
	 w_i_delyl_we <='1';
	 w_i_ex_we <='1';
	 w_i_ey_we <='1';
	 w_i_rhse_we <='1';
	 w_i_rhsl_we <='1';
	 w_i_x1l_we <='1';
	 w_i_y1l_we <='1';
	 w_i_x2l_we <='1';
	 w_i_y2l_we <='1';
	 w_i_sqlnl_we <='1';
	 w_i_ssq_we <='1';
	 w_i_l_we <='1';
	 w_i_le_we <='1';
	 w_i_li_we <='1';
	 wait for PERIOD;
	 w_i_delxl_we <='0';
	 w_i_delyl_we <='0';
	 w_i_ex_we <='0';
	 w_i_ey_we <='0';
	 w_i_rhse_we <='0';
	 w_i_rhsl_we <='0';
	 w_i_x1l_we <='0';
	 w_i_y1l_we <='0';
	 w_i_x2l_we <='0';
	 w_i_y2l_we <='0';
	 w_i_sqlnl_we <='0';
	 w_i_ssq_we <='0';
	 w_i_l_we <='0';
	 w_i_le_we <='0';
	 w_i_li_we <='0';
	 wait for PERIOD;

	 w_start <= '1';
	 wait for PERIOD;
	 w_start <= '0';
         wait until w_o_done = '1';
         wait for PERIOD;
         
         --assert false
         --  report "Done signal found"
        --   severity note;
         assert w_o_ssq = x"3F5D38E4"
           report "ssq wasn't set correctly to 3F5D38E4 "& to_string(w_o_ssq)
             &" tb_photon_variable.vhd."
           severity error;
         
	 --===================================================================
	 --test 4th (and final) condition
         wait for PERIOD*3;
	 
	 w_reset <= '0' ;
         w_start <= '0' ;
	 w_i_delxl <= (others => '0');
	 w_i_delxl_we <= '0';
	 w_i_delyl <= (others => '0');
	 w_i_delyl_we <= '0';
	 w_i_ex <= (others => '0');
	 w_i_ex_we <= '0';
	 w_i_ey <= (others => '0');
	 w_i_ey_we <= '0';
	 w_i_rhse <= (others => '0');
	 w_i_rhse_we <= '0';
	 w_i_rhsl <= (others => '0');
	 w_i_rhsl_we <= '0';
	 w_i_x1l <= (others => '0');
	 w_i_x1l_we <= '0';
	 w_i_y1l <= (others => '0');
	 w_i_y1l_we <= '0';
	 w_i_x2l <= (others => '0');
	 w_i_x2l_we <= '0';
	 w_i_y2l <= (others => '0');
	 w_i_y2l_we <= '0';
	 w_i_sqlnl <= (others => '0');
	 w_i_sqlnl_we <= '0';
	 w_i_ssq <= (others => '0');
	 w_i_ssq_we <= '0';
	 w_i_l <= (others => '0');
	 w_i_l_we <= '0';
	 w_i_le <= (others => '0');
	 w_i_le_we <= '0';
	 w_i_li <= (others => '0');
	 w_i_li_we <= '0';
         wait for PERIOD ;
         w_reset <= '1';
         wait for PERIOD;
         w_reset <= '0';
         wait for PERIOD*8;
	 w_i_delxl <= x"BDF05B00";
	 w_i_delyl <= x"BDFAC848";
	 w_i_ex <= x"BF7EE0EC";
	 w_i_ey <= x"3DBF7B99";
	 w_i_rhse <= x"BDBF72B5";
	 w_i_rhsl <= x"3E2D0E8F";
	 w_i_x1l <= x"3F472D5A";
	 w_i_y1l <= x"BF20D307";
	 w_i_x2l <= x"3F2921FA";
	 w_i_y2l <= x"BF402C10";
	 w_i_sqlnl <= x"3CEBAB45";
	 w_i_ssq <= x"00000000";
	 w_i_l <= x"0000000A";
	 w_i_le <= x"0000000B";
	 w_i_li <= x"0000000C";

         wait for PERIOD;
	 w_i_delxl_we <='1';
	 w_i_delyl_we <='1';
	 w_i_ex_we <='1';
	 w_i_ey_we <='1';
	 w_i_rhse_we <='1';
	 w_i_rhsl_we <='1';
	 w_i_x1l_we <='1';
	 w_i_y1l_we <='1';
	 w_i_x2l_we <='1';
	 w_i_y2l_we <='1';
	 w_i_sqlnl_we <='1';
	 w_i_ssq_we <='1';
	 w_i_l_we <='1';
	 w_i_le_we <='1';
	 w_i_li_we <='1';
	 wait for PERIOD;
	 w_i_delxl_we <='0';
	 w_i_delyl_we <='0';
	 w_i_ex_we <='0';
	 w_i_ey_we <='0';
	 w_i_rhse_we <='0';
	 w_i_rhsl_we <='0';
	 w_i_x1l_we <='0';
	 w_i_y1l_we <='0';
	 w_i_x2l_we <='0';
	 w_i_y2l_we <='0';
	 w_i_sqlnl_we <='0';
	 w_i_ssq_we <='0';
	 w_i_l_we <='0';
	 w_i_le_we <='0';
	 w_i_li_we <='0';
	 wait for PERIOD;

	 w_start <= '1';
	 wait for PERIOD;
	 w_start <= '0';
         wait until w_o_done = '1';
         wait for PERIOD;
         
         assert w_o_ssq = x"3FD5A428"
           report "ssq wasn't set correctly to 3FD5A428 "& to_string(w_o_ssq)
             &" tb_photon_variable.vhd."
           severity error;
         assert false
           report "All done signals found"
           severity note;
         

         wait;
      end process STIMULI;
end tb_trident_design_arch;


--- Test input
--- @ asim -lib work tb_trident_design
--- @ run 8000ns
--- @ exit





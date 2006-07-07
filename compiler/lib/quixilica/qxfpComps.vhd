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


--*****************************************************************************
-- $Header$
--*****************************************************************************
-- Task: qxFP Components package
-- For history information, consult the VSS database
-- Copyright QinetiQ 2003
--*****************************************************************************
library IEEE;
use IEEE.STD_LOGIC_1164.all;

package qxFpComponents is
	

	-- Floating Point Absolute Value
	component fpAbs is
		generic (
			ew 			: integer := 8;									-- exponent width  
			mw 			: integer := 24									-- mantissa width
			);
		port (
			a 		: in std_logic_vector (mw+ew-1 downto 0);  -- input
			q 		: out std_logic_vector (mw+ew-1 downto 0)  -- inverted output
        	);		
	end component fpAbs;

	-- Floating Point Inverter
	component fpInv is
		generic (
			ew 			: integer := 8;									-- exponent width  
			mw 			: integer := 24									-- mantissa width
			);
		port (
			a 		: in std_logic_vector (mw+ew-1 downto 0);  -- input
			q 		: out std_logic_vector (mw+ew-1 downto 0)  -- inverted output
        	);		
	end component fpInv;

	-- Floating Point Adder
	component fpAdd is
		generic (
			ew 			: integer := 8;									-- exponent width  
			mw 			: integer := 24									-- mantissa width
			);
		port (
			clk 	  	: in std_logic;                           		-- synchronous clock	 
			a 		  	: in std_logic_vector (mw+ew-1 downto 0); 		-- operand input
			b 		  	: in std_logic_vector (mw+ew-1 downto 0); 		-- operand input
			aInv 	  	: in std_logic := '0';							-- inverts sign bit of a input 
			bInv 	  	: in std_logic := '0';							-- inverts sign bit of b input
			s 		  	: out std_logic_vector (mw+ew-1 downto 0);		-- sum output
			invalidOp 	: out std_logic;								-- active high flag for invalid operation exception
			overflow  	: out std_logic;                          		-- active high flag for overflow exception
			underflow 	: out std_logic);                         		-- active high flag for underflow exception
	end component fpAdd;
	
	-- Floating Point Multiplyer
	component fpMul is
		generic (
			ew 			: integer := 8;									-- exponent width  
			mw 			: integer := 24;								-- mantissa width
			embedded	: boolean := false								-- Build using the embedded multiplier
			);							
		port (													
			clk       : in std_logic;									-- synchronous clock                                      
			a         : in std_logic_vector (mw+ew-1 downto 0);			-- operand input                                          
			b         : in std_logic_vector (mw+ew-1 downto 0); 		-- operand input
			aInv	  : in std_logic := '0'; 							-- inverts sign bit of a input
			bInv      : in std_logic := '0';							-- inverts sign bit of b input
			p         : out std_logic_vector (mw+ew-1 downto 0);		-- product output                                             
			invalidOp : out std_logic;									-- active high flag for invalid operation exception
			overflow  : out std_logic;                          		-- active high flag for overflow exception
			underflow : out std_logic);                      			-- active high flag for underflow exception
	end component fpMul;
	
	-- Floating Point Divider
	component fpdiv is
		generic (
			ew 			: integer := 8;									-- exponent width  
			mw 			: integer := 24									-- mantissa width
			);						   
		port (
			clk 		: in std_logic;									-- synchronous clock 
			n 			: in std_logic_vector (mw+ew-1 downto 0);		-- operand input                                        
			d 			: in std_logic_vector (mw+ew-1 downto 0); 		-- operand input
			nInv 		: in std_logic := '0';							-- inverts sign bit of a input
			dInv 		: in std_logic := '0';							-- inverts sign bit of b input
			q 			: out std_logic_vector (mw+ew-1 downto 0);		-- quotient output
			invalidOp 	: out std_logic;								-- active high flag for invalid operation exception
			divByZero 	: out std_logic;								-- active high flag for divide by zero exception
			overflow  	: out std_logic;                          		-- active high flag for overflow exception
			underflow 	: out std_logic);                         		-- active high flag for underflow exception
	end component fpdiv;
	
	--Floating point Square Root
	component fpSqrt is
		generic (
			ew 			: integer := 8;									-- exponent width  
			mw 			: integer := 24									-- mantissa width
			);   
		port (
			clk 		: in std_logic;									-- synchronous clock                                    
			a 			: in std_logic_vector (mw+ew-1 downto 0); 		-- operand input                                                                 
			aInv 		: in std_logic := '0';							-- inverts result
			q 			: out std_logic_vector (mw+ew-1 downto 0);		-- quotient output
			invalidOp 	: out std_logic;								-- active high flag for invalid operation exception
			overflow  	: out std_logic;                          		-- active high flag for overflow exception
			underflow 	: out std_logic);                         		-- active high flag for underflow exception
	end component fpSqrt;
	
	--  ***************************************************************************
	--  EDF component declarations
	--  ***************************************************************************
	
	-- ADD:COMPS_START
component fpADD8mw_4ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (11 downto 0);
	b 		  : in std_logic_vector (11 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (11 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD8mw_4ew;

component fpADD8mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (13 downto 0);
	b 		  : in std_logic_vector (13 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (13 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD8mw_6ew;

component fpADD10mw_4ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (13 downto 0);
	b 		  : in std_logic_vector (13 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (13 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD10mw_4ew;

component fpADD10mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (15 downto 0);
	b 		  : in std_logic_vector (15 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (15 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD10mw_6ew;

component fpADD12mw_4ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (15 downto 0);
	b 		  : in std_logic_vector (15 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (15 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD12mw_4ew;

component fpADD12mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (17 downto 0);
	b 		  : in std_logic_vector (17 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (17 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD12mw_6ew;

component fpADD14mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (19 downto 0);
	b 		  : in std_logic_vector (19 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (19 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD14mw_6ew;

component fpADD14mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (21 downto 0);
	b 		  : in std_logic_vector (21 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (21 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD14mw_8ew;

component fpADD16mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (21 downto 0);
	b 		  : in std_logic_vector (21 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (21 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD16mw_6ew;

component fpADD16mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (23 downto 0);
	b 		  : in std_logic_vector (23 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (23 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD16mw_8ew;

component fpADD17mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (22 downto 0);
	b 		  : in std_logic_vector (22 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (22 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD17mw_6ew;

component fpADD17mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (24 downto 0);
	b 		  : in std_logic_vector (24 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (24 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD17mw_8ew;

component fpADD18mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (23 downto 0);
	b 		  : in std_logic_vector (23 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (23 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD18mw_6ew;

component fpADD18mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (25 downto 0);
	b 		  : in std_logic_vector (25 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (25 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD18mw_8ew;

component fpADD18mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (27 downto 0);
	b 		  : in std_logic_vector (27 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD18mw_10ew;

component fpADD20mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (25 downto 0);
	b 		  : in std_logic_vector (25 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (25 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD20mw_6ew;

component fpADD20mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (27 downto 0);
	b 		  : in std_logic_vector (27 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD20mw_8ew;

component fpADD20mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (29 downto 0);
	b 		  : in std_logic_vector (29 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD20mw_10ew;

component fpADD22mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (27 downto 0);
	b 		  : in std_logic_vector (27 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD22mw_6ew;

component fpADD22mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (29 downto 0);
	b 		  : in std_logic_vector (29 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD22mw_8ew;

component fpADD22mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (31 downto 0);
	b 		  : in std_logic_vector (31 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (31 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD22mw_10ew;

component fpADD24mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (29 downto 0);
	b 		  : in std_logic_vector (29 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD24mw_6ew;

component fpADD24mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (31 downto 0);
	b 		  : in std_logic_vector (31 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (31 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD24mw_8ew;

component fpADD24mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (33 downto 0);
	b 		  : in std_logic_vector (33 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (33 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD24mw_10ew;

component fpADD53mw_11ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (63 downto 0);
	b 		  : in std_logic_vector (63 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	s 		  : out std_logic_vector (63 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpADD53mw_11ew;

	-- ADD:END
	
	-- MUL:COMPS_START
component fpMUL8mw_4ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (11 downto 0);
	b 		  : in std_logic_vector (11 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (11 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL8mw_4ew;

component fpMUL8mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (13 downto 0);
	b 		  : in std_logic_vector (13 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (13 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL8mw_6ew;

component fpMUL10mw_4ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (13 downto 0);
	b 		  : in std_logic_vector (13 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (13 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL10mw_4ew;

component fpMUL10mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (15 downto 0);
	b 		  : in std_logic_vector (15 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (15 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL10mw_6ew;

component fpMUL12mw_4ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (15 downto 0);
	b 		  : in std_logic_vector (15 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (15 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL12mw_4ew;

component fpMUL12mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (17 downto 0);
	b 		  : in std_logic_vector (17 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (17 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL12mw_6ew;

component fpMUL14mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (19 downto 0);
	b 		  : in std_logic_vector (19 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (19 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL14mw_6ew;

component fpMUL14mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (21 downto 0);
	b 		  : in std_logic_vector (21 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (21 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL14mw_8ew;

component fpMUL16mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (21 downto 0);
	b 		  : in std_logic_vector (21 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (21 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL16mw_6ew;

component fpMUL16mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (23 downto 0);
	b 		  : in std_logic_vector (23 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (23 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL16mw_8ew;

component fpMUL17mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (22 downto 0);
	b 		  : in std_logic_vector (22 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (22 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL17mw_6ew;

component fpMUL17mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (24 downto 0);
	b 		  : in std_logic_vector (24 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (24 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL17mw_8ew;

component fpMUL18mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (23 downto 0);
	b 		  : in std_logic_vector (23 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (23 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL18mw_6ew;

component fpMUL18mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (25 downto 0);
	b 		  : in std_logic_vector (25 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (25 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL18mw_8ew;

component fpMUL18mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (27 downto 0);
	b 		  : in std_logic_vector (27 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL18mw_10ew;

component fpMUL20mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (25 downto 0);
	b 		  : in std_logic_vector (25 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (25 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL20mw_6ew;

component fpMUL20mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (27 downto 0);
	b 		  : in std_logic_vector (27 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL20mw_8ew;

component fpMUL20mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (29 downto 0);
	b 		  : in std_logic_vector (29 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL20mw_10ew;

component fpMUL22mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (27 downto 0);
	b 		  : in std_logic_vector (27 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL22mw_6ew;

component fpMUL22mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (29 downto 0);
	b 		  : in std_logic_vector (29 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL22mw_8ew;

component fpMUL22mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (31 downto 0);
	b 		  : in std_logic_vector (31 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (31 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL22mw_10ew;

component fpMUL24mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (29 downto 0);
	b 		  : in std_logic_vector (29 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL24mw_6ew;

component fpMUL24mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (31 downto 0);
	b 		  : in std_logic_vector (31 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (31 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL24mw_8ew;

component fpMUL24mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (33 downto 0);
	b 		  : in std_logic_vector (33 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (33 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL24mw_10ew;

component fpMUL53mw_11ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (63 downto 0);
	b 		  : in std_logic_vector (63 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (63 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMUL53mw_11ew;

	-- MUL:END
	
	-- MULE:COMPS_START
component fpMULE8mw_4ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (11 downto 0);
	b 		  : in std_logic_vector (11 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (11 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE8mw_4ew;

component fpMULE8mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (13 downto 0);
	b 		  : in std_logic_vector (13 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (13 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE8mw_6ew;

component fpMULE10mw_4ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (13 downto 0);
	b 		  : in std_logic_vector (13 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (13 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE10mw_4ew;

component fpMULE10mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (15 downto 0);
	b 		  : in std_logic_vector (15 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (15 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE10mw_6ew;

component fpMULE12mw_4ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (15 downto 0);
	b 		  : in std_logic_vector (15 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (15 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE12mw_4ew;

component fpMULE12mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (17 downto 0);
	b 		  : in std_logic_vector (17 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (17 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE12mw_6ew;

component fpMULE14mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (19 downto 0);
	b 		  : in std_logic_vector (19 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (19 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE14mw_6ew;

component fpMULE14mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (21 downto 0);
	b 		  : in std_logic_vector (21 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (21 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE14mw_8ew;

component fpMULE16mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (21 downto 0);
	b 		  : in std_logic_vector (21 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (21 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE16mw_6ew;

component fpMULE16mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (23 downto 0);
	b 		  : in std_logic_vector (23 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (23 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE16mw_8ew;

component fpMULE17mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (22 downto 0);
	b 		  : in std_logic_vector (22 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (22 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE17mw_6ew;

component fpMULE17mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (24 downto 0);
	b 		  : in std_logic_vector (24 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (24 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE17mw_8ew;

component fpMULE18mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (23 downto 0);
	b 		  : in std_logic_vector (23 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (23 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE18mw_6ew;

component fpMULE18mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (25 downto 0);
	b 		  : in std_logic_vector (25 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (25 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE18mw_8ew;

component fpMULE18mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (27 downto 0);
	b 		  : in std_logic_vector (27 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE18mw_10ew;

component fpMULE20mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (25 downto 0);
	b 		  : in std_logic_vector (25 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (25 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE20mw_6ew;

component fpMULE20mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (27 downto 0);
	b 		  : in std_logic_vector (27 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE20mw_8ew;

component fpMULE20mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (29 downto 0);
	b 		  : in std_logic_vector (29 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE20mw_10ew;

component fpMULE22mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (27 downto 0);
	b 		  : in std_logic_vector (27 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE22mw_6ew;

component fpMULE22mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (29 downto 0);
	b 		  : in std_logic_vector (29 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE22mw_8ew;

component fpMULE22mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (31 downto 0);
	b 		  : in std_logic_vector (31 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (31 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE22mw_10ew;

component fpMULE24mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (29 downto 0);
	b 		  : in std_logic_vector (29 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE24mw_6ew;

component fpMULE24mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (31 downto 0);
	b 		  : in std_logic_vector (31 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (31 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE24mw_8ew;

component fpMULE24mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (33 downto 0);
	b 		  : in std_logic_vector (33 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (33 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE24mw_10ew;

component fpMULE53mw_11ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (63 downto 0);
	b 		  : in std_logic_vector (63 downto 0);
	aInv 	  : in std_logic := '0';
	bInv 	  : in std_logic := '0';
	p 		  : out std_logic_vector (63 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpMULE53mw_11ew;

	-- MULE:END
	
	-- SQRT:COMPS_START
component fpSQRT8mw_4ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (11 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (11 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT8mw_4ew;

component fpSQRT8mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (13 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (13 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT8mw_6ew;

component fpSQRT10mw_4ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (13 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (13 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT10mw_4ew;

component fpSQRT10mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (15 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (15 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT10mw_6ew;

component fpSQRT12mw_4ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (15 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (15 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT12mw_4ew;

component fpSQRT12mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (17 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (17 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT12mw_6ew;

component fpSQRT14mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (19 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (19 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT14mw_6ew;

component fpSQRT14mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (21 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (21 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT14mw_8ew;

component fpSQRT16mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (21 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (21 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT16mw_6ew;

component fpSQRT16mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (23 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (23 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT16mw_8ew;

component fpSQRT17mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (22 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (22 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT17mw_6ew;

component fpSQRT17mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (24 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (24 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT17mw_8ew;

component fpSQRT18mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (23 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (23 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT18mw_6ew;

component fpSQRT18mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (25 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (25 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT18mw_8ew;

component fpSQRT18mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (27 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT18mw_10ew;

component fpSQRT20mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (25 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (25 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT20mw_6ew;

component fpSQRT20mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (27 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT20mw_8ew;

component fpSQRT20mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (29 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT20mw_10ew;

component fpSQRT22mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (27 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT22mw_6ew;

component fpSQRT22mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (29 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT22mw_8ew;

component fpSQRT22mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (31 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (31 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT22mw_10ew;

component fpSQRT24mw_6ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (29 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT24mw_6ew;

component fpSQRT24mw_8ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (31 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (31 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT24mw_8ew;

component fpSQRT24mw_10ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (33 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (33 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT24mw_10ew;

component fpSQRT53mw_11ew is
port (
	clk 	  : in std_logic;
	a 		  : in std_logic_vector (63 downto 0);
	aInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (63 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpSQRT53mw_11ew;

	-- SQRT:END
	
	-- DIV:COMPS_START
component fpDIV8mw_4ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (11 downto 0);
	d 		  : in std_logic_vector (11 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (11 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV8mw_4ew;

component fpDIV8mw_6ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (13 downto 0);
	d 		  : in std_logic_vector (13 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (13 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV8mw_6ew;

component fpDIV10mw_4ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (13 downto 0);
	d 		  : in std_logic_vector (13 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (13 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV10mw_4ew;

component fpDIV10mw_6ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (15 downto 0);
	d 		  : in std_logic_vector (15 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (15 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV10mw_6ew;

component fpDIV12mw_4ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (15 downto 0);
	d 		  : in std_logic_vector (15 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (15 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV12mw_4ew;

component fpDIV12mw_6ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (17 downto 0);
	d 		  : in std_logic_vector (17 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (17 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV12mw_6ew;

component fpDIV14mw_6ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (19 downto 0);
	d 		  : in std_logic_vector (19 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (19 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV14mw_6ew;

component fpDIV14mw_8ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (21 downto 0);
	d 		  : in std_logic_vector (21 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (21 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV14mw_8ew;

component fpDIV16mw_6ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (21 downto 0);
	d 		  : in std_logic_vector (21 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (21 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV16mw_6ew;

component fpDIV16mw_8ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (23 downto 0);
	d 		  : in std_logic_vector (23 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (23 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV16mw_8ew;

component fpDIV17mw_6ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (22 downto 0);
	d 		  : in std_logic_vector (22 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (22 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV17mw_6ew;

component fpDIV17mw_8ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (24 downto 0);
	d 		  : in std_logic_vector (24 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (24 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV17mw_8ew;

component fpDIV18mw_6ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (23 downto 0);
	d 		  : in std_logic_vector (23 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (23 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV18mw_6ew;

component fpDIV18mw_8ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (25 downto 0);
	d 		  : in std_logic_vector (25 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (25 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV18mw_8ew;

component fpDIV18mw_10ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (27 downto 0);
	d 		  : in std_logic_vector (27 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV18mw_10ew;

component fpDIV20mw_6ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (25 downto 0);
	d 		  : in std_logic_vector (25 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (25 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV20mw_6ew;

component fpDIV20mw_8ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (27 downto 0);
	d 		  : in std_logic_vector (27 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV20mw_8ew;

component fpDIV20mw_10ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (29 downto 0);
	d 		  : in std_logic_vector (29 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV20mw_10ew;

component fpDIV22mw_6ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (27 downto 0);
	d 		  : in std_logic_vector (27 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (27 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV22mw_6ew;

component fpDIV22mw_8ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (29 downto 0);
	d 		  : in std_logic_vector (29 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV22mw_8ew;

component fpDIV22mw_10ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (31 downto 0);
	d 		  : in std_logic_vector (31 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (31 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV22mw_10ew;

component fpDIV24mw_6ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (29 downto 0);
	d 		  : in std_logic_vector (29 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (29 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV24mw_6ew;

component fpDIV24mw_8ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (31 downto 0);
	d 		  : in std_logic_vector (31 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (31 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV24mw_8ew;

component fpDIV24mw_10ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (33 downto 0);
	d 		  : in std_logic_vector (33 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (33 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV24mw_10ew;

component fpDIV53mw_11ew is
port (
	clk 	  : in std_logic;
	n 		  : in std_logic_vector (63 downto 0);
	d 		  : in std_logic_vector (63 downto 0);
	nInv 	  : in std_logic := '0';
	dInv 	  : in std_logic := '0';
	q 		  : out std_logic_vector (63 downto 0);
	invalidOp : out std_logic;							-- active high flag for invalid operation exception
	divByZero : out std_logic;							-- active high flag for divide by zero operation exception
	overflow  : out std_logic;                          -- active high flag for overflow exception
	underflow : out std_logic);                         -- active high flag for underflow exception
end component fpDIV53mw_11ew;

	-- DIV:END
	
	
	-- Latency calculation functions
	function fpAddDelay(ew, mw : integer) return integer;
	function fpMulDelay(ew, mw : integer; embedded : boolean) return integer; 
	function fpDivDelay(ew, mw : integer) return integer;
	function fpSqrtDelay(ew, mw : integer) return integer;
end package;

package body qxFpComponents is
	-- Latency calculation function for the floating point Adder
	function fpAddDelay(ew, mw : integer) return integer is
		variable delay : integer;
	begin					 
		delay := 7;
		if mw <= 15 then
			delay := delay + 2;
		else
			delay := delay + 3;
		end if;
		delay := delay + (ew+3)/4 - 1;
		return delay;
	end function fpAddDelay;
	
	-- Latency calculation function for the floating point Multiplier
	function fpMulDelay(ew, mw : integer; embedded : boolean) return integer is
	begin
	if embedded = true then	
		if mw <= 17 then
			return 5;
		elsif mw > 17 and mw <= 34 then
			return 6;
		elsif mw = 53 then
			return 11; 
		else
			return 0;
			report "Unsupported value of mw/ew for fpMul. Invalid latency returned.";
		end if;
	else
		if mw <= 10 then
			return 6;
		elsif mw > 10 and mw <= 22 then
			return 7;
		elsif mw > 22 and mw <= 24 then
			return 8;
		elsif mw = 53 then
			return 9;
		else
			return 0;
			report "Unsupported value of mw/ew for fpMul. Invalid latency returned.";
		end if;
	end if;
	end function fpMulDelay;
	
	-- Latency calculation function for the floating point Divider
	function fpDivDelay(ew, mw : integer) return integer is
	begin
		return mw+3;
	end function fpDivDelay;
	
	-- Latency calculation function for the floating point Square Root
	function fpSqrtDelay(ew, mw : integer) return integer is
	begin
		return mw+3;
	end function fpSqrtDelay;
	
	
	
	
	
	
	
	
end package body;


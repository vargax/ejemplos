-- Vhdl test bench created from schematic D:\Jorge Mario\Dropbox\Digitales\LAB2\circuito.sch - Sat Feb 05 23:55:58 2011
--
-- Notes: 
-- 1) This testbench template has been automatically generated using types
-- std_logic and std_logic_vector for the ports of the unit under test.
-- Xilinx recommends that these types always be used for the top-level
-- I/O of a design in order to guarantee that the testbench will bind
-- correctly to the timing (post-route) simulation model.
-- 2) To use this template as your testbench, change the filename to any
-- name of your choice with the extension .vhd, and use the "Source->Add"
-- menu in Project Navigator to import the testbench. Then
-- edit the user defined section below, adding code to generate the 
-- stimulus for your design.
--
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE ieee.std_logic_unsigned.ALL;
USE ieee.numeric_std.ALL;

LIBRARY UNISIM;
USE UNISIM.Vcomponents.ALL;
ENTITY circuito_circuito_sch_tb IS
END circuito_circuito_sch_tb;
ARCHITECTURE behavioral OF circuito_circuito_sch_tb IS 

   COMPONENT lab2
   PORT( A	:	IN	STD_LOGIC; 
          B	:	IN	STD_LOGIC; 
          C	:	IN	STD_LOGIC; 
          D	:	IN	STD_LOGIC; 
          Z	:	OUT	STD_LOGIC);
   END COMPONENT;

   SIGNAL A	:	STD_LOGIC;
   SIGNAL B	:	STD_LOGIC;
   SIGNAL C	:	STD_LOGIC;
   SIGNAL D	:	STD_LOGIC;
   SIGNAL Z	:	STD_LOGIC;
	SIGNAL TMP	:	STD_LOGIC_VECTOR (3 DOWNTO 0):="0000";

BEGIN

   UUT: lab2 PORT MAP(
		A => A, 
		B => B, 
		C => C, 
		D => D, 
		Z => Z
   );

-- *** Test Bench - User Defined Section ***
   tb : PROCESS
   BEGIN
	report "Fundamentos de Sistemas Digitales  - Practica 2";
      WAIT  for 10 ns;
		for i in 0 to 16 loop
			A <= TMP(0);
			B <= TMP(1);
			C <= TMP(2);
			D <= TMP(3);
			TMP <= TMP +1;
			WAIT  for 10 ns;
		end loop;
		wait;
   END PROCESS;

	
-- *** End Test Bench - User Defined Section ***

END;

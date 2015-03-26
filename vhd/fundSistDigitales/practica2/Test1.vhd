-- Vhdl test bench created from schematic F:\Datos\workspace\Prueba\Esquematico.sch - Sun Aug 21 10:12:22 2011
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
ENTITY Esquematico_Esquematico_sch_tb IS
END Esquematico_Esquematico_sch_tb;
ARCHITECTURE behavioral OF Esquematico_Esquematico_sch_tb IS 

   COMPONENT Esquematico
   PORT( A : IN STD_LOGIC;
	B : IN STD_LOGIC;
	C : IN STD_LOGIC;
	D : IN STD_LOGIC;
	E : IN STD_LOGIC;
	F : IN STD_LOGIC;
	G : IN STD_LOGIC;
	Z : OUt STD_LOGIC);
   END COMPONENT;

	SIGNAL A : STD_LOGIC;
	SIGNAL B : STD_LOGIC;
	SIGNAL C : STD_LOGIC;
	SIGNAL D : STD_LOGIC;
	SIGNAL E : STD_LOGIC;
	SIGNAL F : STD_LOGIC;
	SIGNAL G : STD_LOGIC;
	SIGNAL Z : STD_LOGIC;
	SIGNAL TMP	:	STD_LOGIC_VECTOR (6 DOWNTO 0):="0000000";

BEGIN

   UUT: Esquematico PORT MAP(
		A => A,
		B => B,
		C => C,
		D => D,
		E => E,
		F => F,
		G => G,
		Z => Z
   );

-- *** Test Bench - User Defined Section ***
   tb : PROCESS
   BEGIN
		report "Fundamentos de Sistemas Digitales  - Practica 2";
      WAIT  for 10 ns;
--		for i in 0 to 131072 loop
--			A <= TMP(0);
--			B <= TMP(1);
--			C <= TMP(2);
--			D <= TMP(3);
--			E <= TMP(4);
--			F <= TMP(5);
--			G <= TMP(6);
--			TMP <= TMP +1;
--			WAIT  for 10 ns;
--		end loop;
		for i in 0 to 5 loop
			case (i) is 
				when 4 => 
					TMP <= "0100010";--5			
				when 3 => 
					TMP <= "0101010";--4
				when 2 => 
					TMP <= "1100110";--3
				when 1 => 
					TMP <= "1101110";--2
				when 0 => 
					TMP <= "1110111";--1
				when 5 => 
					TMP <= "0001000";--6
			 end case;
			A <= TMP(0);
			B <= TMP(1);
			C <= TMP(2);
			D <= TMP(3);
			E <= TMP(4);
			F <= TMP(5);
			G <= TMP(6);
			WAIT  for 10 ns;
		end loop;
		
		--wait;
   END PROCESS;
-- *** End Test Bench - User Defined Section ***

END;

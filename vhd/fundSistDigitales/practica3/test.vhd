--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   10:36:37 09/03/2011
-- Design Name:   
-- Module Name:   C:/Users/Digitales/Desktop/digPractica3/test.vhd
-- Project Name:  Display
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: practica3
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY test IS
END test;
 
ARCHITECTURE behavior OF test IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT practica3
    PORT(
         LED1 : IN  std_logic;
         LED2 : IN  std_logic;
         LED3 : IN  std_logic;
         LED4 : IN  std_logic;
         LED5 : IN  std_logic;
         LED6 : IN  std_logic;
         LED7 : IN  std_logic;
         SEGA : OUT  std_logic;
         SEGB : OUT  std_logic;
         SEGC : OUT  std_logic;
         SEGD : OUT  std_logic;
         SEGE : OUT  std_logic;
         SEGF : OUT  std_logic;
         SEGG : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal LED1 : std_logic := '0';
   signal LED2 : std_logic := '0';
   signal LED3 : std_logic := '0';
   signal LED4 : std_logic := '0';
   signal LED5 : std_logic := '0';
   signal LED6 : std_logic := '0';
   signal LED7 : std_logic := '0';

 	--Outputs
   signal SEGA : std_logic;
   signal SEGB : std_logic;
   signal SEGC : std_logic;
   signal SEGD : std_logic;
   signal SEGE : std_logic;
   signal SEGF : std_logic;
   signal SEGG : std_logic;
  
BEGIN

	-- Instantiate the Unit Under Test (UUT)
   uut: practica3 PORT MAP (
          LED1 => LED1,
          LED2 => LED2,
          LED3 => LED3,
          LED4 => LED4,
          LED5 => LED5,
          LED6 => LED6,
          LED7 => LED7,
          SEGA => SEGA,
          SEGB => SEGB,
          SEGC => SEGC,
          SEGD => SEGD,
          SEGE => SEGE,
          SEGF => SEGF,
          SEGG => SEGG
        );
		  
		  PROCESS
		  BEGIN
		  
				WAIT FOR 10 NS; --1
				LED1 <= '1';
				LED2 <= '1';
				LED3 <= '1';
				LED4 <= '0';
				LED5 <= '1';
				LED6 <= '1';
				LED7 <= '1';
				
				WAIT FOR 10 NS; --2
				LED1 <= '1';
				LED2 <= '1';
				LED3 <= '0';
				LED4 <= '1';
				LED5 <= '1';
				LED6 <= '1';
				LED7 <= '0';
				
				WAIT FOR 10 NS; --3
				LED1 <= '1';
				LED2 <= '1';
				LED3 <= '0';
				LED4 <= '0';
				LED5 <= '1';
				LED6 <= '1';
				LED7 <= '0';
				
				WAIT FOR 10 NS; --4
				LED1 <= '0';
				LED2 <= '1';
				LED3 <= '0';
				LED4 <= '1';
				LED5 <= '0';
				LED6 <= '1';
				LED7 <= '0';
				
				WAIT FOR 10 NS; --5
				LED1 <= '0';
				LED2 <= '1';
				LED3 <= '0';
				LED4 <= '0';
				LED5 <= '0';
				LED6 <= '1';
				LED7 <= '0';
				
				WAIT FOR 10 NS; --6
				LED1 <= '0';
				LED2 <= '0';
				LED3 <= '0';
				LED4 <= '1';
				LED5 <= '0';
				LED6 <= '0';
				LED7 <= '0';
			END PROCESS;
END;

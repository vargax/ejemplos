--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   20:35:23 10/23/2011
-- Design Name:   
-- Module Name:   C:/Users/cvargasc/Desktop/practica6new/practica6new/testControl.vhd
-- Project Name:  practica6new
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: control
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
 
ENTITY testControl IS
END testControl;
 
ARCHITECTURE behavior OF testControl IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT control
    PORT(
         reset 	: in  std_logic;
			clock		: in  std_logic;
			est0		: in 	STD_LOGIC;
			est1		: in 	STD_LOGIC;
			est2		: in 	STD_LOGIC;
			est3		: in 	STD_LOGIC;
			est4		: in 	STD_LOGIC;
			est5		: in 	STD_LOGIC;
			est6		: in 	STD_LOGIC;
			est7		: in 	STD_LOGIC;
			motor0	: out STD_LOGIC;
			motor1	: out STD_LOGIC;
			motor2	: out STD_LOGIC;
			motor3	: out STD_LOGIC;
         parlante : out std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal reset : std_logic := '1';
	signal clock : std_logic := '1';
	signal est0	 : std_logic := '1';
	signal est1	 : std_logic := '1';
	signal est2	 : std_logic := '1';
	signal est3	 : std_logic := '1';
	signal est4	 : std_logic := '1';
	signal est5	 : std_logic := '1';
	signal est6	 : std_logic := '1';
	signal est7	 : std_logic := '1';

 	--Outputs
	signal motor0		: std_logic;
	signal motor1		: std_logic;
	signal motor2		: std_logic;
	signal motor3		: std_logic;
   signal parlante 	: std_logic;
 
   constant clock_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: control PORT MAP (
          reset 		=> reset,
			 clock 		=> clock,
			 est0			=> est0,
			 est1			=> est1,
			 est2			=> est2,
			 est3			=> est3,
			 est4			=> est4,
			 est5			=> est5,
			 est6			=> est6,
			 est7			=> est7,
			 motor0 		=> motor0,
			 motor1 		=> motor1,
			 motor2 		=> motor2,
			 motor3 		=> motor3,
          parlante	=> parlante
        );

   -- Clock process definitions
   clock_process :process
   begin
		clock <= '0';
		wait for clock_period/2;
		clock <= '1';
		wait for clock_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin	
      -- hold reset state for 100 ns.
      wait for 100 ns;	
		reset <= '0';
      wait for clock_period*10;
		reset <= '1';
		est3	<= '0';
		wait for clock_period*5;
		est3	<= '1';
		wait for clock_period*10;
		est2	<= '0';
		wait for clock_period*5;
		est2	<= '1';
		wait for clock_period*20;
		est2	<= '0';
		wait for clock_period*5;
		est2	<= '1';
--		est3	<= '1';
--		wait for clock_period*5;
--		est3	<= '0';
--		wait for clock_period*5;
--		est7	<= '1';
--		wait for clock_period*5;
--		est7	<= '0';		
      -- insert stimulus here 

      wait;
   end process;

END;

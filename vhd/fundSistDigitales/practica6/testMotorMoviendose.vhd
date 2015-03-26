--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   18:28:39 10/23/2011
-- Design Name:   
-- Module Name:   C:/Users/cvargasc/Desktop/practica6new/practica6new/testMotorMoviendose.vhd
-- Project Name:  practica6new
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: motorMoviendose
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
 
ENTITY testMotorMoviendose IS
END testMotorMoviendose;
 
ARCHITECTURE behavior OF testMotorMoviendose IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT motorMoviendose
    PORT(
         clock : IN  std_logic;
         reset : IN  std_logic;
         motor0 : OUT  std_logic;
         motor1 : OUT  std_logic;
         motor2 : OUT  std_logic;
         motor3 : OUT  std_logic;
         parlante : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal clock : std_logic := '0';
   signal reset : std_logic := '0';

 	--Outputs
   signal motor0 : std_logic;
   signal motor1 : std_logic;
   signal motor2 : std_logic;
   signal motor3 : std_logic;
   signal parlante : std_logic;

   -- Clock period definitions
   constant clock_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: motorMoviendose PORT MAP (
          clock => clock,
          reset => reset,
          motor0 => motor0,
          motor1 => motor1,
          motor2 => motor2,
          motor3 => motor3,
          parlante => parlante
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

      wait for clock_period*10;
		--reset <= '1';
		wait for clock_period*10;
		--reset <= '0';
		wait for clock_period*100;
   end process;

END;

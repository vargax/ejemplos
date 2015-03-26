--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   15:32:48 11/24/2011
-- Design Name:   
-- Module Name:   C:/Users/Digitales/Desktop/roadWarriorV2/roadWarrior/testRoadWarrior.vhd
-- Project Name:  roadWarrior
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: roadWarrior
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
 
ENTITY testRoadWarrior IS
END testRoadWarrior;
 
ARCHITECTURE behavior OF testRoadWarrior IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT roadWarrior
    PORT(
         clk : IN  std_logic;
			avance : IN std_logic;
			reset : IN std_logic;
         memoria : OUT  std_logic_vector(6 downto 0);
         registro : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal clk : std_logic := '0';
	signal avance : std_logic := '0';
	signal reset : std_logic := '0';

 	--Outputs
   signal memoria : std_logic_vector(6 downto 0);
   signal registro : std_logic;

   -- Clock period definitions
   constant clk_period : time := 10 ns;
	constant avance_period : time := 200 ns;
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: roadWarrior PORT MAP (
          clk => clk,
			 avance => avance,
			 reset => reset,
          memoria => memoria,
          registro => registro
        );

   -- Clock process definitions
   clk_process :process
   begin
		clk <= '0';
		wait for clk_period/2;
		clk <= '1';
		wait for clk_period/2;
   end process;
	
	avance_process :process
	begin
		avance <= '0';
		wait for avance_period;
		avance <= '1';
		wait for clk_period;
	end process;
   -- Stimulus process
   stim_proc: process
   begin		
      wait for 10 ns;	
		reset <= '1';
      wait for clk_period*3;
		reset <= '0';
		wait for clk_period*3;
      -- insert stimulus here 

      wait;
   end process;

END;

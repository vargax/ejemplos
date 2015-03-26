--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   07:47:53 11/03/2011
-- Design Name:   
-- Module Name:   /home/cvargasc/Documentos/Uniandes/201120/Fundamentos de Sistemas Digitales/Laboratorios/practica7/practica7/testTest.vhd
-- Project Name:  practica7
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: test
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
 
ENTITY testTest IS
END testTest;
 
ARCHITECTURE behavior OF testTest IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT test
    PORT(
         clk : IN  std_logic;
         reset : IN  std_logic;
         filasOut : OUT  std_logic_vector(7 downto 0);
         columnasOut : OUT  std_logic_vector(7 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal clk : std_logic := '0';
   signal reset : std_logic := '0';

 	--Outputs
   signal filasOut : std_logic_vector(7 downto 0);
   signal columnasOut : std_logic_vector(7 downto 0);

   -- Clock period definitions
   constant clk_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: test PORT MAP (
          clk => clk,
          reset => reset,
          filasOut => filasOut,
          columnasOut => columnasOut
        );

   -- Clock process definitions
   clk_process :process
   begin
		clk <= '0';
		wait for clk_period/2;
		clk <= '1';
		wait for clk_period/2;
   end process;
   -- Stimulus process
   stim_proc: process
   begin		
      reset <= '1';
      wait for clk_period*3;
		reset <= '0';
		wait;
   end process;
END;

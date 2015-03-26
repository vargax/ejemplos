--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   08:18:56 10/31/2011
-- Design Name:   
-- Module Name:   /home/cvargasc/Documentos/Uniandes/201120/Fundamentos de Sistemas Digitales/Laboratorios/practica7/practica7/testProcesoBolita.vhd
-- Project Name:  practica7
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: procesoBolita
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
 
ENTITY testProcesoBolita IS
END testProcesoBolita;
 
ARCHITECTURE behavior OF testProcesoBolita IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT procesoBolita
    PORT(
         clk 			: IN  std_logic;
         reset 		: IN  std_logic;
         inicio 		: IN  std_logic_vector(7 downto 0);
         filasOut 	: OUT  std_logic_vector(7 downto 0);
         columnasOut : OUT  std_logic_vector(7 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal clk : std_logic := '0';
   signal reset : std_logic := '0';
   signal inicio : std_logic_vector(7 downto 0) := (others => '0');

 	--Outputs
   signal filasOut : std_logic_vector(7 downto 0);
   signal columnasOut : std_logic_vector(7 downto 0);

   -- Clock period definitions
   constant clk_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: procesoBolita PORT MAP (
          clk => clk,
          reset => reset,
          inicio => inicio,
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
      reset 	<= '1';
		inicio	<= "10000000";
      wait for 10 ns;	
		reset <= '0';
		inicio	<= "01000000";
      wait for clk_period*8;
		inicio	<= "00100000";
      wait for clk_period*8;
		inicio	<= "00000001";
      wait for clk_period*8;
		inicio	<= "00000010";
      wait for clk_period*8;
		inicio	<= "00000100";		
		wait for clk_period*8;
		inicio	<= "00001000";
		wait for clk_period*8;
		inicio	<= "00010000";
      wait;
   end process;

END;

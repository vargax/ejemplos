--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   12:09:45 11/02/2011
-- Design Name:   
-- Module Name:   /home/cvargasc/Documentos/Uniandes/201120/Fundamentos de Sistemas Digitales/Laboratorios/practica7/practica7/testGeneradorAleatorio.vhd
-- Project Name:  practica7
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: generadorAleatorio
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
 
ENTITY testGeneradorAleatorio IS
END testGeneradorAleatorio;
 
ARCHITECTURE behavior OF testGeneradorAleatorio IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT generadorAleatorio
    PORT(
         clk : IN  std_logic;
         reset : IN  std_logic;
         derecha : IN  std_logic;
         izquierda : IN  std_logic;
         inicioOut : OUT  std_logic_vector(7 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal clk : std_logic := '0';
   signal reset : std_logic := '0';
   signal derecha : std_logic := '0';
   signal izquierda : std_logic := '0';

 	--Outputs
   signal inicioOut : std_logic_vector(7 downto 0);

   -- Clock period definitions
   constant clk_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: generadorAleatorio PORT MAP (
          clk => clk,
          reset => reset,
          derecha => derecha,
          izquierda => izquierda,
          inicioOut => inicioOut
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
		wait for clk_period*3;
		derecha <= '1';
      wait for clk_period*6;
		derecha <= '0';
		wait for clk_period*20;
		izquierda <= '1';
		wait for clk_period*6;
		izquierda <= '0';
   end process;

END;

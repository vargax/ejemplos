--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   16:30:32 10/30/2011
-- Design Name:   
-- Module Name:   /home/cvargasc/Documentos/Uniandes/201120/Fundamentos de Sistemas Digitales/Laboratorios/practica7/practica7/testProcesoJugador.vhd
-- Project Name:  practica7
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: procesoJugador
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
 
ENTITY testProcesoJugador IS
END testProcesoJugador;
 
ARCHITECTURE behavior OF testProcesoJugador IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT procesoJugador
    PORT(
         reset 		: IN  std_logic;
         clk 			: IN  std_logic;
         derecha 		: IN  std_logic;
         izquierda	: IN  std_logic;
         filasOut 	: OUT  std_logic_vector(7 downto 0);
         columnasOut : OUT  std_logic_vector(7 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal reset 		: std_logic := '0';
   signal clk 			: std_logic := '0';
   signal derecha 	: std_logic := '0';
   signal izquierda 	: std_logic := '0';

 	--Outputs
   signal filasOut 	: std_logic_vector(7 downto 0);
   signal columnasOut: std_logic_vector(7 downto 0);

   -- Clock period definitions
   constant clk_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: procesoJugador PORT MAP (
          reset => reset,
          clk => clk,
          derecha => derecha,
          izquierda => izquierda,
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
      wait for 100 ns;	
		reset <= '0';
      wait for clk_period*3;
		derecha <= '1';
		wait for clk_period*3;
		derecha <= '0';
		wait for clk_period*3;
		derecha <= '1';
		wait for clk_period*3;
		derecha <= '0';
		wait for clk_period*3;
		derecha <= '1';
		wait for clk_period*3;
		derecha <= '0';
		wait for clk_period*3;
		izquierda <= '1';
		wait for clk_period*3;
		izquierda <= '0';
		wait for clk_period*3;
		izquierda <= '1';
		wait for clk_period*3;
		izquierda <= '0';
		wait for clk_period*3;
		derecha <= '1';
		wait for clk_period*3;
		derecha <= '0';
		wait for clk_period*3;
		derecha <= '1';
		wait for clk_period*3;
		derecha <= '0';
		wait for clk_period*3;
		derecha <= '1';
		wait for clk_period*3;
		derecha <= '0';
		wait for clk_period*3;
		derecha <= '1';
		wait for clk_period*3;
		derecha <= '0';
		wait for clk_period*3;
		derecha <= '1';
		wait for clk_period*3;
		derecha <= '0';
		wait for clk_period*3;
		izquierda <= '1';
		wait for clk_period*3;
		izquierda <= '0';
		wait for clk_period*3;
		izquierda <= '1';
		wait for clk_period*3;
		izquierda <= '0';
		wait for clk_period*3;
		izquierda <= '1';
		wait for clk_period*3;
		izquierda <= '0';
		wait for clk_period*3;
		izquierda <= '1';
		wait for clk_period*3;
		izquierda <= '0';
		wait for clk_period*3;
		izquierda <= '1';
		wait for clk_period*3;
		izquierda <= '0';
		wait for clk_period*3;
		izquierda <= '1';
		wait for clk_period*3;
		izquierda <= '0';
      wait;
   end process;

END;

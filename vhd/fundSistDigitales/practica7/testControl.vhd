--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   12:09:17 11/02/2011
-- Design Name:   
-- Module Name:   /home/cvargasc/Documentos/Uniandes/201120/Fundamentos de Sistemas Digitales/Laboratorios/practica7/practica7/testControl.vhd
-- Project Name:  practica7
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
         clk : IN  std_logic;
         reset : IN  std_logic;
         inicioIn : IN  std_logic;
         filasBolita : IN  std_logic_vector(7 downto 0);
         columnasJugador : IN  std_logic_vector(7 downto 0);
         columnasBolita : IN  std_logic_vector(7 downto 0);
         visible : OUT  std_logic;
         inicioOut : OUT  std_logic;
         puntajeOut : OUT  std_logic_vector(3 downto 0);
         filas : OUT  std_logic_vector(7 downto 0);
         columnas : OUT  std_logic_vector(7 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal clk : std_logic := '0';
   signal reset : std_logic := '0';
   signal inicioIn : std_logic := '0';
   signal filasBolita : std_logic_vector(7 downto 0) := (others => '0');
   signal columnasJugador : std_logic_vector(7 downto 0) := (others => '0');
   signal columnasBolita : std_logic_vector(7 downto 0) := (others => '0');

 	--Outputs
   signal visible : std_logic;
   signal inicioOut : std_logic;
   signal puntajeOut : std_logic_vector(3 downto 0);
   signal filas : std_logic_vector(7 downto 0);
   signal columnas : std_logic_vector(7 downto 0);

   -- Clock period definitions
   constant clk_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: control PORT MAP (
          clk => clk,
          reset => reset,
          inicioIn => inicioIn,
          filasBolita => filasBolita,
          columnasJugador => columnasJugador,
          columnasBolita => columnasBolita,
          visible => visible,
          inicioOut => inicioOut,
          puntajeOut => puntajeOut,
          filas => filas,
          columnas => columnas
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
		-- SIMULANDO UNA VICTORIA
      reset <= '1';
      wait for clk_period*3;
		reset <= '0';
      wait for clk_period*3;
		inicioIn <= '0';
		-- Bolita en la fila de arriba
      filasBolita 		<= "00000001";
		-- Columnas Bolita y Jugador coinciden
      columnasJugador 	<= "01000000";
      columnasBolita 	<= "01000000";
		wait for clk_period*3;
		-- Inicio el juego
		inicioIn <= '1';
		wait for clk_period*3;
		-- Libero el botn de inicio
		inicioIn <= '0';
		wait for clk_period*3;
		-- Bolita cae 1 vez
		filasBolita 		<= "10000000";
		wait for clk_period*3;
		filasBolita 		<= "01000000";
		wait for clk_period*3;
		-- Bolita cae 2 vez
		filasBolita 		<= "10000000";
		wait for clk_period*3;
		filasBolita 		<= "01000000";
		wait for clk_period*3;
		-- Bolita cae 3 vez
		filasBolita 		<= "10000000";
		wait for clk_period*3;
		filasBolita 		<= "01000000";
		wait for clk_period*3;
		-- Bolita cae 4 vez
		filasBolita 		<= "10000000";
		wait for clk_period*3;
		filasBolita 		<= "01000000";
		wait for clk_period*3;
		-- Bolita cae 5 vez
		filasBolita 		<= "10000000";
		wait for clk_period*3;
		filasBolita 		<= "01000000";
		wait for clk_period*3;
		-- Bolita cae 6 vez
		filasBolita 		<= "10000000";
		wait for clk_period*3;
		filasBolita 		<= "01000000";
		wait for clk_period*3;
		-- Bolita cae 7 vez
		filasBolita 		<= "10000000";
		wait for clk_period*3;
		filasBolita 		<= "01000000";
		wait for clk_period*3;
		-- Bolita cae 8 vez
		filasBolita 		<= "10000000";
		wait for clk_period*3;
		filasBolita 		<= "01000000";
		wait for clk_period*3;
		-- Bolita cae 9 vez
		filasBolita 		<= "10000000";
		wait for clk_period*3;
		filasBolita 		<= "01000000";
		wait for clk_period*3;
		-- Bolita cae 10 vez
		filasBolita 		<= "10000000";
	end process;
END;
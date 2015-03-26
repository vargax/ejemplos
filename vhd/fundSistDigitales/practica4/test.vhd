-- Vhdl test bench created from schematic C:\Users\Carlo\Documents\documentos carlo\universidad de los andes\3er semestre\trabajos\Sistemas Digitales\practica4\estructural.sch - Tue Sep 13 14:11:02 2011
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
USE ieee.numeric_std.ALL;
LIBRARY UNISIM;
USE UNISIM.Vcomponents.ALL;
ENTITY estructural_estructural_sch_tb IS
END estructural_estructural_sch_tb;
ARCHITECTURE behavioral OF estructural_estructural_sch_tb IS 

   COMPONENT estructural
   PORT( boton_habilitador	:	IN	STD_LOGIC; 
          led1	:	IN	STD_LOGIC; 
          led2	:	IN	STD_LOGIC; 
          led3	:	IN	STD_LOGIC; 
          led4	:	IN	STD_LOGIC; 
          led5	:	IN	STD_LOGIC; 
          led6	:	IN	STD_LOGIC; 
          led7	:	IN	STD_LOGIC; 
          D1A2	:	OUT	STD_LOGIC; 
          D1A1	:	OUT	STD_LOGIC; 
          D1A0	:	OUT	STD_LOGIC; 
          D2A0	:	OUT	STD_LOGIC; 
          D2A1	:	OUT	STD_LOGIC; 
          D2A2	:	OUT	STD_LOGIC; 
          RBO	:	OUT	STD_LOGIC; 
          RBI	:	OUT	STD_LOGIC; 
          D3A0	:	OUT	STD_LOGIC; 
          D3A1	:	OUT	STD_LOGIC; 
          D3A2	:	OUT	STD_LOGIC; 
          LED_TEST	:	OUT	STD_LOGIC; 
          switch11	:	IN	STD_LOGIC; 
          switch12	:	IN	STD_LOGIC; 
          switch13	:	IN	STD_LOGIC; 
          switch21	:	IN	STD_LOGIC; 
          switch22	:	IN	STD_LOGIC; 
          switch23	:	IN	STD_LOGIC; 
          switch31	:	IN	STD_LOGIC; 
          switch32	:	IN	STD_LOGIC; 
          switch33	:	IN	STD_LOGIC);
   END COMPONENT;
-- Entradas
   SIGNAL boton_habilitador	:	STD_LOGIC :='0';
   SIGNAL led1	:	STD_LOGIC:='0';
   SIGNAL led2	:	STD_LOGIC:='0';
   SIGNAL led3	:	STD_LOGIC:='0';
   SIGNAL led4	:	STD_LOGIC:='0';
   SIGNAL led5	:	STD_LOGIC:='0';
   SIGNAL led6	:	STD_LOGIC:='0';
   SIGNAL led7	:	STD_LOGIC:='0';
	SIGNAL switch11	:	STD_LOGIC:='0';
   SIGNAL switch12	:	STD_LOGIC:='0';
   SIGNAL switch13	:	STD_LOGIC:='0';
   SIGNAL switch21	:	STD_LOGIC:='0';
   SIGNAL switch22	:	STD_LOGIC:='0';
   SIGNAL switch23	:	STD_LOGIC:='0';
   SIGNAL switch31	:	STD_LOGIC:='0';
   SIGNAL switch32	:	STD_LOGIC:='0';
   SIGNAL switch33	:	STD_LOGIC:='0';
-- Salidas
   SIGNAL D1A2	:	STD_LOGIC;
   SIGNAL D1A1	:	STD_LOGIC;
   SIGNAL D1A0	:	STD_LOGIC;
   SIGNAL D2A0	:	STD_LOGIC;
   SIGNAL D2A1	:	STD_LOGIC;
   SIGNAL D2A2	:	STD_LOGIC;
   SIGNAL RBO	:	STD_LOGIC;
   SIGNAL RBI	:	STD_LOGIC;
   SIGNAL D3A0	:	STD_LOGIC;
   SIGNAL D3A1	:	STD_LOGIC;
   SIGNAL D3A2	:	STD_LOGIC;
   SIGNAL LED_TEST	:	STD_LOGIC;
	
BEGIN

   UUT: estructural PORT MAP(
		boton_habilitador => boton_habilitador, 
		led1 => led1, 
		led2 => led2, 
		led3 => led3, 
		led4 => led4, 
		led5 => led5, 
		led6 => led6, 
		led7 => led7, 
		D1A2 => D1A2, 
		D1A1 => D1A1, 
		D1A0 => D1A0, 
		D2A0 => D2A0, 
		D2A1 => D2A1, 
		D2A2 => D2A2, 
		RBO => RBO, 
		RBI => RBI, 
		D3A0 => D3A0, 
		D3A1 => D3A1, 
		D3A2 => D3A2, 
		LED_TEST => LED_TEST, 
		switch11 => switch11, 
		switch12 => switch12, 
		switch13 => switch13, 
		switch21 => switch21, 
		switch22 => switch22, 
		switch23 => switch23, 
		switch31 => switch31, 
		switch32 => switch32, 
		switch33 => switch33
   );

-- *** Test Bench - User Defined Section ***
   tb : PROCESS
   BEGIN
      WAIT FOR 10 NS;
		report "Entradas del dado (0101001)";
		boton_habilitador <= '0';
		led1 <= '0'; 
		led2 <= '1'; 
		led3 <= '0'; 
		led4 <= '1'; 
		led5 <= '0'; 
		led6 <= '0'; 
		led7 <= '1';
		report "Num Oculto 231";
		--**-- TURNO 1
		report "Entradas jugador";
		switch11 <= '0'; 
		switch12 <= '1';
		switch13 <= '0';
		switch21 <= '1';
		switch22 <= '1';
		switch23 <= '0';
		switch31 <= '0';
		switch32 <= '1';
		switch33 <= '1';
		wait for 10 ns;
		report "Num ingresado (263) -> 1 pica, 1 fija";
		report "Probando funcionamiento display";
		assert D1A2 = switch11 report "Display D1A2 no representa la entrada del jugador" severity WARNING;
		assert D1A1 = switch12 report "Display D1A1 no representa la entrada del jugador" severity WARNING;
		assert D1A0 = switch13 report "Display D1A0 no representa la entrada del jugador" severity WARNING;
		assert D2A2 = switch21 report "Display D2A2 no representa la entrada del jugador" severity WARNING;
		assert D2A1 = switch22 report "Display D2A1 no representa la entrada del jugador" severity WARNING;
		assert D2A0 = switch23 report "Display D2A0 no representa la entrada del jugador" severity WARNING;
		assert D3A2 = switch31 report "Display D3A2 no representa la entrada del jugador" severity WARNING;
		assert D3A1 = switch32 report "Display D3A1 no representa la entrada del jugador" severity WARNING;
		assert D3A0 = switch33 report "Display D3A0 no representa la entrada del jugador" severity WARNING;
		
		assert RBO ='1' report "RBO debía ser 1" severity WARNING;
		assert RBI ='1' report "RBI debía ser 1" severity WARNING;
		report "Probando funcionamiento LED";
		assert LED_TEST = '1' report "El LED debía estar apagado";
		report "Jugador pulsa boton habilitador (turno 1)";
		WAIT FOR 10 NS;
		boton_habilitador <= '1';
		wait for 10 NS;
		report "Probando resultados de picas y fijas";
		assert D1A2 = '0' report "Display D1A2 debía mostrar 1 fijas" severity WARNING;
		assert D1A1 = '0' report "Display D1A1 debía mostrar 1 fijas" severity WARNING;
		assert D1A0 = '1' report "Display D1A0 debía mostrar 1 fijas" severity WARNING;
		assert D2A2 = '0' report "Display D2A2 debía mostrar 1 picas" severity WARNING;
		assert D2A1 = '0' report "Display D2A1 debía mostrar 1 picas" severity WARNING;
		assert D2A0 = '1' report "Display D2A0 debía mostrar 1 picas" severity WARNING;
		assert D3A2 = '0' report "Display D3A2 debía estar apagado" severity WARNING;
		assert D3A1 = '0' report "Display D3A1 debía estar apagado" severity WARNING;
		assert D3A0 = '0' report "Display D3A0 debía estar apagado" severity WARNING;
		
		assert RBO ='0' report "RBO debía ser 0" severity WARNING;
		assert RBI ='0' report "RBI debía ser 0" severity WARNING;
		report "Probando funcionamiento LED";
		assert LED_TEST = '1' report "El LED debía estar apagado";		
		report "Jugador libera boton habilitador (fin turno 1)";
		wait FOR 10 NS; 
		boton_habilitador <= '0';
		wait for 10 NS;
		--**-- TURNO 2
		report "Jugador cambia las entradas";
		switch11 <= '0'; 
		switch12 <= '1';
		switch13 <= '0';
		switch21 <= '0';
		switch22 <= '1';
		switch23 <= '1';
		switch31 <= '1';
		switch32 <= '0';
		switch33 <= '0';
		wait for 10 ns;
		report "Num ingresado (234) -> 0 picas, 2 fijas";
		report "Probando funcionamiento display";
		assert D1A2 = switch11 report "Display D1A2 no representa la entrada del jugador" severity WARNING;
		assert D1A1 = switch12 report "Display D1A1 no representa la entrada del jugador" severity WARNING;
		assert D1A0 = switch13 report "Display D1A0 no representa la entrada del jugador" severity WARNING;
		assert D2A2 = switch21 report "Display D2A2 no representa la entrada del jugador" severity WARNING;
		assert D2A1 = switch22 report "Display D2A1 no representa la entrada del jugador" severity WARNING;
		assert D2A0 = switch23 report "Display D2A0 no representa la entrada del jugador" severity WARNING;
		assert D3A2 = switch31 report "Display D3A2 no representa la entrada del jugador" severity WARNING;
		assert D3A1 = switch32 report "Display D3A1 no representa la entrada del jugador" severity WARNING;
		assert D3A0 = switch33 report "Display D3A0 no representa la entrada del jugador" severity WARNING;
		
		assert RBO ='1' report "RBO debía ser 1" severity WARNING;
		assert RBI ='1' report "RBI debía ser 1" severity WARNING;
		report "Probando funcionamiento LED";
		assert LED_TEST = '1' report "El LED debía estar apagado";
		report "Jugador pulsa boton habilitador (turno 2)";
		WAIT FOR 10 NS;
		boton_habilitador <= '1';
		wait for 10 NS;
		report "Probando resultados de picas y fijas";
		assert D1A2 = '0' report "Display D1A2 debía mostrar 2 fijas" severity WARNING;
		assert D1A1 = '1' report "Display D1A1 debía mostrar 2 fijas" severity WARNING;
		assert D1A0 = '0' report "Display D1A0 debía mostrar 2 fijas" severity WARNING;
		assert D2A2 = '0' report "Display D2A2 debía mostrar 0 picas" severity WARNING;
		assert D2A1 = '0' report "Display D2A1 debía mostrar 0 picas" severity WARNING;
		assert D2A0 = '0' report "Display D2A0 debía mostrar 0 picas" severity WARNING;
		assert D3A2 = '0' report "Display D3A2 debía estar apagado" severity WARNING;
		assert D3A1 = '0' report "Display D3A1 debía estar apagado" severity WARNING;
		assert D3A0 = '0' report "Display D3A0 debía estar apagado" severity WARNING;
		
		assert RBO ='0' report "RBO debía ser 0" severity WARNING;
		assert RBI ='0' report "RBI debía ser 0" severity WARNING;		
		report "Probando funcionamiento LED";
		assert LED_TEST = '1' report "El LED debía estar apagado";
		report "Jugador libera boton habilitador (fin turno 2)";
		wait FOR 10 NS; 
		boton_habilitador <= '0';
		wait for 10 NS;
		--**-- TURNO 3 -- FIN DEL JUEGO
		report "Jugador cambia las entradas";
		switch11 <= '0'; 
		switch12 <= '1';
		switch13 <= '0';
		switch21 <= '0';
		switch22 <= '1';
		switch23 <= '1';
		switch31 <= '0';
		switch32 <= '0';
		switch33 <= '1';
		wait for 10 ns;
		report "Num ingresado (231) -> 0 picas, 3 fijas";
		report "Probando funcionamiento display";
		assert D1A2 = switch11 report "Display D1A2 no representa la entrada del jugador" severity WARNING;
		assert D1A1 = switch12 report "Display D1A1 no representa la entrada del jugador" severity WARNING;
		assert D1A0 = switch13 report "Display D1A0 no representa la entrada del jugador" severity WARNING;
		assert D2A2 = switch21 report "Display D2A2 no representa la entrada del jugador" severity WARNING;
		assert D2A1 = switch22 report "Display D2A1 no representa la entrada del jugador" severity WARNING;
		assert D2A0 = switch23 report "Display D2A0 no representa la entrada del jugador" severity WARNING;
		assert D3A2 = switch31 report "Display D3A2 no representa la entrada del jugador" severity WARNING;
		assert D3A1 = switch32 report "Display D3A1 no representa la entrada del jugador" severity WARNING;
		assert D3A0 = switch33 report "Display D3A0 no representa la entrada del jugador" severity WARNING;
		
		assert RBO ='1' report "RBO debía ser 1" severity WARNING;
		assert RBI ='1' report "RBI debía ser 1" severity WARNING;
		report "Probando funcionamiento LED";
		assert LED_TEST = '0' report "El LED debía estar encendido";
		report "Jugador pulsa boton habilitador (turno 3 -- FIN DEL JUEGO)";
		WAIT FOR 10 NS;
		boton_habilitador <= '1';
		wait for 10 NS;
		report "Probando resultados de picas y fijas";
		assert D1A2 = '0' report "Display D1A2 debía mostrar 3 fijas" severity WARNING;
		assert D1A1 = '1' report "Display D1A1 debía mostrar 3 fijas" severity WARNING;
		assert D1A0 = '1' report "Display D1A0 debía mostrar 3 fijas" severity WARNING;
		assert D2A2 = '0' report "Display D2A2 debía mostrar 0 picas" severity WARNING;
		assert D2A1 = '0' report "Display D2A1 debía mostrar 0 picas" severity WARNING;
		assert D2A0 = '0' report "Display D2A0 debía mostrar 0 picas" severity WARNING;
		assert D3A2 = '0' report "Display D3A2 debía estar apagado" severity WARNING;
		assert D3A1 = '0' report "Display D3A1 debía estar apagado" severity WARNING;
		assert D3A0 = '0' report "Display D3A0 debía estar apagado" severity WARNING;
		
		assert RBO ='0' report "RBO debía ser 0" severity WARNING;
		assert RBI ='0' report "RBI debía ser 0" severity WARNING;		
		report "Probando funcionamiento LED";
		assert LED_TEST = '0' report "El LED debía estar encendido";
		report "Jugador libera boton habilitador (FIN DEL JUEGO)";
		wait FOR 10 NS; 
		boton_habilitador <= '0';
		wait for 10 NS;
   END PROCESS;
-- *** End Test Bench - User Defined Section ***

END;

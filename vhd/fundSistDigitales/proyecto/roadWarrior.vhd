----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    15:13:01 11/24/2011 
-- Design Name: 
-- Module Name:    roadWarrior - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee.std_logic_unsigned.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity roadWarrior is
	Port (	clk			: in std_logic;
				avance		: in std_logic;
				reset			: in std_logic;
				memoria		: out std_logic_vector(6 downto 0);
				registro		: out std_logic
	);
end roadWarrior;

architecture Behavioral of roadWarrior is
	signal contadorRegistro : std_logic_vector(2 downto 0) := (others => '0');
	signal contadorCuadros : std_logic_vector(6 downto 0) := (others => '0');
	signal contadorMemoria : std_logic_vector(6 downto 0) := (others => '0');
begin
	-- Proceso memoria
	process(clk,avance,reset,contadorRegistro,contadorCuadros,contadorMemoria)
	begin
		if reset = '1' then
			contadorCuadros <= "0000000";
			contadorMemoria <= "0000000";
			contadorRegistro <= "000";
			
			memoria <= "0000000";
			registro <= '0';
		elsif clk'event and clk = '1' then
			-- Proceso para el registro y la memoria
			case contadorRegistro is
				when "000" => 
					registro <= '1';
					contadorMemoria <= contadorCuadros;
				when others => 
					registro <= '0';
					contadorMemoria <= contadorMemoria + 1;
			end case;
			contadorRegistro <= contadorRegistro + 1;
			memoria <= contadorMemoria;
			-- Proceso para el avance del escenario
			if avance = '1' then
				contadorCuadros <= contadorCuadros + 1;
			end if;
		end if;
	end process;
end Behavioral;
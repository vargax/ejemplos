----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:05:49 10/31/2011 
-- Design Name: 
-- Module Name:    preEscalador - Behavioral 
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity preEscalador is
   Port (clk 			: in  STD_LOGIC;
			reset 		: in  STD_LOGIC;
			caidaBolitaOut : out  STD_LOGIC;
			multiplexorOut : out  STD_LOGIC
	);
end preEscalador;

architecture Behavioral of preEscalador is
	signal contCaidaBolita	: integer range 0 to 1100000;--***
	signal contMultiplexor	: integer range 0 to 33000;--***
	signal caidaBolita : STD_LOGIC;
	signal multiplexor : STD_LOGIC;
begin
	process(reset,clk,contCaidaBolita,contMultiplexor,caidaBolita,multiplexor)
	begin
		if reset = '1' then
			caidaBolita <= '0';
			multiplexor	<= '0';
			contCaidaBolita	<= 1100000;--***
			contMultiplexor	<= 33000;--***
		elsif clk'event and clk = '1' then
			if contCaidaBolita = 0 then
				contCaidaBolita <= 1100000;--***
				if caidaBolita = '1' then
					caidaBolita <= '0'; 
				else
					caidaBolita <= '1';
				end if;
			else
				contCaidaBolita <= contCaidaBolita - 1;
			end if;
			
			if contMultiplexor = 0 then
				contMultiplexor <= 33000; --***
				if multiplexor = '1' then
					multiplexor <= '0';
				else
					multiplexor <= '1';
				end if;
			else
				contMultiplexor <= contMultiplexor - 1;
			end if;
		end if;
	end process;
	
	process(clk,reset,multiplexor,caidaBolita)
	begin
		if reset = '1' then
			caidaBolitaOut <= '0';
			multiplexorOut	<= '0';
		elsif clk'event and clk = '1' then
			multiplexorOut <= multiplexor;
			caidaBolitaOut	<= caidaBolita;
		end if;
	end process;
end Behavioral;


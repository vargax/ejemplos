----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    07:20:35 11/03/2011 
-- Design Name: 
-- Module Name:    juegoBolita - Behavioral 
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

entity test is
	Port(	clk 			: in  STD_LOGIC;
         reset 		: in  STD_LOGIC;
         filasOut 	: out  STD_LOGIC_VECTOR (7 downto 0);
         columnasOut	: out  STD_LOGIC_VECTOR (7 downto 0)
	);
end test;

architecture Behavioral of test is
	type estados is (e1,e2);
	signal estado : estados;
begin
	process(clk,reset)
	begin
		if reset = '1' then
			estado		<= e1;
			filasOut 	<= "10000001";
			columnasOut	<= "11111111";
		elsif clk'event and clk = '1' then
			case estado is
				when e1 =>
					columnasOut <= "10000000";
					filasOut		<= "10000000";
					estado		<= e2;
				when e2 =>
					columnasOut <= "01000000";
					filasOut		<= "01000000";
					estado		<= e1;
			end case;
		end if;
	end process;
end Behavioral;
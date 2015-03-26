----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    14:49:02 09/11/2011 
-- Design Name: 
-- Module Name:    picas - Behavioral 
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

entity picas is
    Port ( sw1 : in  STD_LOGIC_VECTOR (2 downto 0);
           sw2 : in  STD_LOGIC_VECTOR (2 downto 0);
           sw3 : in  STD_LOGIC_VECTOR (2 downto 0);
           nu1 : in  STD_LOGIC_VECTOR (2 downto 0);
           nu2 : in  STD_LOGIC_VECTOR (2 downto 0);
           nu3 : in  STD_LOGIC_VECTOR (2 downto 0);
           picas : out std_logic_vector (2 downto 0));
end picas;

architecture Behavioral of picas is

 


begin

	picas(0) <= (((sw1(0) xnor nu2(0))and(sw1(1) xnor nu2(1))and(sw1(2) xnor nu2(2))) or
					((sw1(0) xnor nu3(0))and(sw1(1) xnor nu3(1))and(sw1(2) xnor nu3(2))));
					
	picas(1) <= (((sw2(0) xnor nu1(0))and(sw2(1) xnor nu1(1))and(sw2(2) xnor nu1(2))) or
					((sw2(0) xnor nu3(0))and(sw2(1) xnor nu3(1))and(sw2(2) xnor nu3(2))));
  
   picas(2) <= (((sw3(0) xnor nu2(0))and(sw3(1) xnor nu2(1))and(sw3(2) xnor nu2(2))) or
					((sw3(0) xnor nu1(0))and(sw3(1) xnor nu1(1))and(sw3(2) xnor nu1(2))));
					




end Behavioral;


----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    22:20:25 09/10/2011 
-- Design Name: 
-- Module Name:    comp_mux_sum - Behavioral 
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

entity comp_mux_sum is
    Port ( nx1 : in  STD_LOGIC_VECTOR (2 downto 0);
           nx2 : in  STD_LOGIC_VECTOR (2 downto 0);
           nux1 : out  STD_LOGIC_VECTOR (2 downto 0);
           nux2 : out  STD_LOGIC_VECTOR (2 downto 0));
end comp_mux_sum;

architecture Behavioral of comp_mux_sum is

component sumador
 Port ( num : in  STD_LOGIC_VECTOR (2 downto 0);
       suma : out  STD_LOGIC_VECTOR (2 downto 0));
end component;

component comparador
 Port ( num1 : in  STD_LOGIC_VECTOR (2 downto 0);
           num2 : in  STD_LOGIC_VECTOR (2 downto 0);
           answer : out  STD_LOGIC);
end component;

component mux

Port ( sel : in  STD_LOGIC;
       n : in  STD_LOGIC_VECTOR (2 downto 0);
       nsum : in  STD_LOGIC_VECTOR (2 downto 0);
		 salida : out  STD_LOGIC_VECTOR (2 downto 0));

end component;

signal s1,s2,s3,s4 : std_logic_vector(2 downto 0);
signal r: bit;

begin

u1: component comparador port map(num1 <= nx1, num2 <= nx2, answer <= r);
u2: sumador port map(num <= nx2, suma <= s1);
u3: mux port map (sel <= r, n <= nx2, nsum <= s1, salida <= s2)

nux1 <= nx1;


end Behavioral;


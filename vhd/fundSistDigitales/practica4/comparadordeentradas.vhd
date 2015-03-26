----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    04:09:18 09/09/2011 
-- Design Name: 
-- Module Name:    comparadordeentradas - Behavioral 
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

entity comparador is
    Port ( A : in  STD_LOGIC;
           B : in  STD_LOGIC;
           C : in  STD_LOGIC;
           D : in  STD_LOGIC;
           E : in  STD_LOGIC;
           F : in  STD_LOGIC;
           G : in  STD_LOGIC;
			  HAB : in STD_LOGIC;
           RESP : out  STD_LOGIC;
			  RETRO : out bit);

end comparador;

architecture Behavioral of comparadordeentradas is

begin


RETRO <= (((A XNOR C)and(B XNOR D)and(C XNOR E)) or((A XNOR E)and(B XNOR F)and(C XNOR G)) or((C XNOR E)and(D XNOR F) and( E XNOR G)) );

RESP <= RETRO AND HAB ;


end Behavioral;


----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:33:47 09/10/2011 
-- Design Name: 
-- Module Name:    eqcomp4 - Behavioral 
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

entity eqcomp4 is
    Port ( a,b : in  STD_LOGIC_VECTOR (3 downto 0);
           aeqb : out  STD_LOGIC);
end eqcomp4;



architecture struct of eqcomp4 is
 signal x : std_logic_vector(0 to 3);
begin

u0: xnor2 port map(a(0),b(0),x(0));
u1: xnor2 port map(a(1),b(1),x(1));
u2: xnor2 port map(a(2),b(2),x(2));
u3: xnor2 port map(a(3),b(3),x(3));
u4: and4 port map(x(0),x(1),x(2),x(3),aeqb);

end struct;


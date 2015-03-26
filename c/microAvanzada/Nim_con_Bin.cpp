#include <stdio.h>  /* Funciones estandar de entrada y salida */
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MC 40

typedef unsigned char u8;
typedef unsigned char * pu8;
typedef union
{
	u8 BYTE;
	struct
	{
		u8 b0 : 1;
		u8 b1 : 1;
		u8 b2 : 1;
		u8 b3 : 1;
		u8 b4 : 1;
		u8 b5 : 1;
		u8 b6 : 1;
		u8 b7 : 1;
	}bit;
}Byte;

int calculo(Byte nim[]);
int ev_un_dig(int *pnum);
int reset(Byte nim[]);
int iguala(Byte n[], Byte nim[]);
int comp_par(Byte n[]);
int imprime_nim(Byte nim[]);
int finalizar(Byte nim[]);
int ev_fin(Byte nim[]);
int cont_unos(Byte nim[], u8 *unos, u8 *ceros, u8 *dir_otros);
int obt_num(void);

int main(void)
{
	int salir, fila, resta;
	Byte nim[4];
	
	reset(nim);

	while(salir!=1){
		
		imprime_nim(nim);

		printf("Ingrese la fila (1-4)\n");
		fila=obt_num();
		while(nim[fila-1].BYTE == 0 || fila < 1 || fila > 4)
		{
			printf("\nError, no se pueden quitar palitos de esta fila.\n");
			printf("Oprima una tecla para continuar\n");
			getchar();
			system("clear");
			printf("Ingrese una nueva fila (1-4)\n");
			fila=obt_num();
		}

		printf("Ingrese el numero de palitos a quitar\n");
		resta=obt_num();
		while(resta > nim[fila-1].BYTE || resta < 1)
		{
			printf("\nError, no se pueden quitar mas de %d palitos de esta fila, ni menos de 1.\n",nim[fila-1].BYTE);
			printf("Oprima una tecla para continuar\n");
			getchar();
			system("clear");
			printf("Ingrese un nuevo numero de palitos a quitar\n");
			resta=obt_num();
		}

		printf("\nfila %d\n",fila);
		printf("resta %d\n",resta);

		nim[fila-1].BYTE -= resta;

		imprime_nim(nim);

		if(finalizar(nim)==0)
			calculo(nim);
		else
		{
			if(ev_fin(nim))
			{
				imprime_nim(nim);
				printf("Game Over\nOprima una tecla para continuar\n");
				getchar();
				reset(nim);
				system("clear");
				printf("Ingrese 1 si desea salir o 0 si no desea salir (0 no salir, 1 salir)\n");
				ev_un_dig(&salir);
				system("clear");
			}
		}
	}
}

int obt_num(void)
{
	int num;
	while(ev_un_dig(&num)==0)
	{
		printf("\nError, el dato ingresado no es valido.\n");
	}
	return num;
}

int cont_unos(Byte nim[], u8 *punos, u8 *pceros, u8 *pdir_otros)
{
	u8 i;
	
	*punos=*pceros=*pdir_otros=0;

	for(i=0;i<4;i++)
	{
		if(nim[i].BYTE==0)
			(*pceros)++;
		else if(nim[i].BYTE==1)
			(*punos)++;
		else
			(*pdir_otros)=i;
	}

	return 0;
}

int ev_fin(Byte nim[])
{
	u8 unos, ceros, dir_otros;
	cont_unos(nim, &unos, &ceros, &dir_otros);
	if(unos==1 && ceros==3)
		return 1;
	else
		return 0;
}

int imprime_nim(Byte nim[])
{
	u8 i,j;

	printf("\n");
	for(i=0;i<4;i++)
	{
		printf("f%d ",i+1);
		for(j=0;j<nim[i].BYTE;j++)
		{
			printf("| ");
		}
		printf("\n");
	}
	printf("\n");
}

int reset(Byte nim[])
{

	nim[0].BYTE = 0;
	nim[0].bit.b0 = 1;

	nim[1].BYTE = 0;
	nim[1].bit.b0 = 1;
	nim[1].bit.b1 = 1;
	
	nim[2].BYTE = 0;
	nim[2].bit.b0 = 1;
	nim[2].bit.b1 = 0;
	nim[2].bit.b2 = 1;

	nim[3].BYTE = 0;
	nim[3].bit.b0 = 1;
	nim[3].bit.b1 = 1;
	nim[3].bit.b2 = 1;
	
	return 0;

}

int ev_un_dig(int *pnum)
{
	int esnum=0,salir=0,i=0;
	char numero[MC];
	gets(numero);
	while(numero[i]!=NULL && salir==0)
	{
		esnum=isdigit(numero[i]);
		if(esnum==0)
			salir=1;
		i++;
	}
	if(esnum!=0)
		*pnum=atoi(numero);
	return (esnum);
}

int comp_par(Byte n[])
{
	u8 i;
	u8 v0, v1, v2;

	v0=v1=v2=0;

	for (i=0;i<4;i++)
	{
		v0 += n[i].bit.b0;
		v1 += n[i].bit.b1;
		v2 += n[i].bit.b2;
	}

	if(v0%2 == 0 && v1%2 == 0 && v2%2 == 0)
	{
		i = 1;
	}
	else
		i = 0;

	return i;
}

int iguala(Byte n[], Byte nim[])
{
	u8 i;

	for (i=0;i<4;i++)
	{
		n[i].BYTE = nim[i].BYTE;
	}
	return 0;
}

int calculo(Byte nim[])
{
	u8 j, salir=0;
	Byte n[4];

	iguala(n,nim);
	
	j=0;
	while (j<4 && salir == 0)
	{
		while(n[j].BYTE > 0 && salir == 0)
		{
			n[j].BYTE--;
			salir=comp_par(n);
		}

		if(salir == 0)
		{
			iguala(n,nim);
			j++;
		}
		else
			iguala(nim,n);
	}
	return salir;
}

int finalizar(Byte nim[])
{
	u8 fin=0, i;
	u8 unos, ceros, dir_otros;

	cont_unos(nim, &unos, &ceros, &dir_otros);

	if(ceros>1)
	{
		if(ceros==3 && unos==0)
		{
			nim[dir_otros].BYTE=1;
			fin=1;
		}

		else if(ceros==2 && unos==1)
		{
			nim[dir_otros].BYTE=0;
			fin=1;
		}
		else if(unos==2)
		{
			i=0;
			while(i<4 && fin==0)
			{
				if(nim[i].BYTE==1)
				{
					nim[i].BYTE=0;
					fin=1;
				}
				i++;
			}
		}
	}
	else if(unos>=3 && ceros==0)
	{
		nim[dir_otros].BYTE=0;
		fin=1;
	}
	else if(unos==2 && ceros==1)
	{
		nim[dir_otros].BYTE=1;
		fin=1;
	}
	
	return fin;
	
}

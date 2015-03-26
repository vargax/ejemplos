// isis1304-111-proyecto2.cpp: define el punto de entrada de la aplicación de consola.
//
// DESARROLLADO POR:
// Camilo Vargas Cabrera, 200612197
// Nombre, carnet

#include "stdlib.h" 
#include "stdio.h"
#include "string.h"

// Estructura de un pixel (verdadero color)
typedef struct pixel
{
	unsigned char red;
	unsigned char green;
	unsigned char blue;
} Pixel;

// La representacion de la imagen
typedef struct img
{
	int ancho;
	int alto;
	Pixel *pixels;
} Imagen;


// Función que carga el bmp en la estructura Imagen
void cargarBMP24 (Imagen * imagen, char * nomArchivoEntrada);

// Funcion que guarda el contenido de la estructura imagen en un archivo binario
void guardarBMP24 (Imagen * imagen, char * nomArchivoSalida);

//Funcion que inserta un mensaje en la imagen usando n bits por Byte
void insertarMensaje(Imagen * img , char mensaje[], int n);

//Funcion que lee un mensaje de una imagen dando la longitud del mensaje y el numero de bits por byte usados
void leerMensaje(Imagen * img,char msg[], int l, int n);

//******************************************************************************
// Función que cambia a nuevoVal el n'esimo bit del caracter pasado como parámetro
void cambiarValor(char *caracter, int nuevoVal, int n);
// Imprime la representación binaria del caracter pasado como parámetro
void printChar(char Char);
//******************************************************************************
// Programa principal
// NO MODIFICAR
int main(int argc, char* argv[])
{
	Imagen *img = (Imagen *) malloc (sizeof (Imagen));
	char msg[1000];
	char op;
	int num;
	int l;
	int i;
	int n;
	char nomArch1 [256] = "";

	if (argc != 2)
	{
		printf ("Faltan argumentos - Debe ser un archivo\n");
		system("pause");
		return -1;
	}

	strcat (nomArch1, argv[1]);

	// Cargar los datos
	cargarBMP24 (img, nomArch1);
	
	printf("Indique la accion\n\t1) insertar mensaje\n\t2) leer mensaje\n\n");//muestra las opciones
	
	op = getch();

    if(op == '1')
    {
        printf("ingrese el mensaje a insertar\n");
        
        gets(msg);//lee la cadena insertada por teclado
        
        printf("longitud mensaje: %d\n",strlen(msg));
        
        num=0;
        printf("ingrese el numero de bits por Byte\n");
        scanf("%d",&num);//lee el numero de bits por bite a usar
        
        insertarMensaje(img,msg,num);//ejecuta el programa para guardar el mensaje en la imagen
        
        guardarBMP24 (img, nomArch1);//guarda la imagen con el mensaje
        
        printf("mensaje insertado\n");
    }
    else if(op =='2')
    {
        printf("ingrese la longitud del mensaje insertado\n");
        
        scanf("%d",&l);//ingresa la longitud del mensaje original
        
        printf("ingrese el numero de bits por Byte\n");
        scanf("%d",&n);//ingresa numero de bits por byte usados
        
        for(i=0;i<l;i++)
        {
            msg[i]=0;
        }
        
        leerMensaje(img,msg, l, n);//saca el mensaje de la imagen y lo guarda en msg
        
        msg[l]=0;
        
        printf("el mensaje es: %s\n",msg);      
    }
    else
    {
    
    }
	
	system ("pause");
	return 0;
}

//Funcion que inserta un mensaje en la imagen usando n bits por Byte
//Debe desarrollar completamente esta funcion
void insertarMensaje(Imagen * img , char mensaje[], int n)
{	
	// Calculo el total de bits que necesito
	int totalBits = 8*strlen(mensaje);
	int faltantes = 8%n;
	printf("Faltantes = %d \n",faltantes);
	// Extraigo el mensaje completo a un arreglo
	char message[totalBits+faltantes];
	int h=0;
	while(h<totalBits){
		int k; for(k=0;k<8;k++) {
			message[h] = (mensaje[h/8]>>k & 1);
			h++;
		}
	}
	// Completando con ceros al final
    int k; for (k=0; k<=faltantes; k++) message[totalBits-1+k] = 0;

	int hh;
	for (hh = sizeof(message)-1; hh>=0;hh--) printf("%u",message[hh]);
	printf("\n");
	
	// Ubicando el primer pixel
	Pixel *p = (img->pixels);
	int i = 0; while (i < sizeof(message)) {
        int j; for (j = n; j > 0; j--) {
            cambiarValor(&(p->red),message[i],j); i++;
        }
        for (j = n; i < sizeof(message) && j > 0; j--) {
            cambiarValor(&(p->green),message[i],j); i++;
        }
        for (j = n; i < sizeof(message) && j > 0; j--) {
            cambiarValor(&(p->blue),message[i],j); i++;
        }
        p = p + 1;
    }
}
// Imprime la representación binaria del caracter pasado como parámetro
void printChar(char Char) {
	int j; for (j = 7; j >= 0; j--)	printf("%d", (Char>>j) & 1);
	printf("\n");
}
// Cambia a nuevoVal el n'esimo bit del caracter pasado como parámetro
// donde 1 es el bit menos significativo y 8 el bit más significativo
void cambiarValor(char *caracter, int nuevoVal, int n) {
    printf("Insertar %d en pos %d de ",nuevoVal,n);	printChar(*caracter);
	if (nuevoVal == 0) {
		*caracter &= ~(1 << (n-1));
	}
	else if (nuevoVal == 1) {
		*caracter |= (1 << (n-1));
	}
    printChar(*caracter);
}


//Funcion que lee un mensaje de una imagen dando la longitud del mensaje y el numero de bits por byte usados
//Debe desarrollar completamente esta funcion
void leerMensaje(Imagen * img,char msg[], int l, int n)
{
	// Un arreglo para almacenar los bits del mensaje
	char message[8*l];
	// Calculo el número de bytes que debo recorrer
	int bytesRecorrer = (8*l)%n != 0 ? ((8*l)/n) + 1 : ((8*l)/n);
	printf("Bytes a recorrer: %d \n", bytesRecorrer);
	
	// Calculo el total de pixeles en la imagen
	int totalPixeles = (img->alto)*(img -> ancho);
    Pixel * pixel = img -> pixels;
	// Contador para el bit actual
	int bit = 0;
	// Recorro los pixeles mientras tenga bytes por recorrer
	int i; for(i=0;i<totalPixeles && bit/8 < bytesRecorrer;i++) {
        Pixel p = *(pixel + i);
        // Apuntadores para cada uno de los componentes del pixel
        unsigned char *r = &p.red;
        unsigned char *g = &p.green;
        unsigned char *b = &p.blue;
        // Recuperando los n bits menos significativos de cada componente
        int j; 
		for (j = 0; j < n; j++) {message[bit] = (*r << j) & 1; bit++;}
        for (j = 0; j < n; j++) {message[bit] = (*g << j) & 1; bit++;}
        for (j = 0; j < n; j++) {message[bit] = (*b << j) & 1; bit++;}
		*(pixel + i) = p;
    }
    
    int hh;
	for (hh = sizeof(message)-1; hh>=0;hh--) printf("%u",message[hh]);
	printf("\n");
    
	msg[0]='a';
	msg[1]='b';
	msg[2]='c';
}

// Lee un archivo en formato BMP y lo almacena en la estructura img
// NO MODIFICAR
void cargarBMP24 (Imagen * imagen, char * nomArchivoEntrada)
{
	// bmpDataOffset almacena la posición inicial de los datos de la imagen. Las otras almacenan el alto y el ancho
	// en pixeles respectivamente
	int bmpDataOffset, bmpHeight, bmpWidth;
	int y;
	int x;
	int	residuo;

	FILE *bitmapFile;	
	bitmapFile = fopen (nomArchivoEntrada, "rb");	
	if (bitmapFile == 0)
	{
		printf ("No ha sido posible cargar el archivo: %s\n", nomArchivoEntrada);
		exit (-1);
	}
	
	fseek (bitmapFile, 10, SEEK_SET); // 10 es la posición del campo "Bitmap Data Offset" del bmp	
	fread (&bmpDataOffset, sizeof (int), 1, bitmapFile);
	
	fseek (bitmapFile, 18, SEEK_SET); // 18 es la posición del campo "height" del bmp
	fread (&bmpWidth, sizeof (int), 1, bitmapFile);
		
	fseek (bitmapFile, 22, SEEK_SET); // 22 es la posición del campo "width" del bmp
	fread (&bmpHeight, sizeof (int), 1, bitmapFile);
	//bmpHeight = -bmpHeight; // Se multiplica 1 para convertirlo en valor positivo
	
	residuo = (bmpWidth * 3) % 4; // Se debe calcular los bits residuales del bmp, que surjen al almacenar en palabras de 16 bits
	
	imagen -> ancho = bmpWidth;
	imagen -> alto = bmpHeight;	
	imagen -> pixels = (Pixel *) calloc (bmpWidth * bmpHeight, sizeof (Pixel));

	fseek (bitmapFile, bmpDataOffset, SEEK_SET); // Se ubica el puntero del archivo al comienzo de los datos
	

	for (y = 0; y < bmpHeight; y++)	
	{
		for ( x= 0; x < bmpWidth; x++) 
		{
			int pos = y * bmpWidth + x;
			fread (&imagen -> pixels [pos].blue, sizeof(unsigned char ), 1, bitmapFile);
			fread (&imagen -> pixels [pos].green, sizeof(unsigned char ), 1, bitmapFile);
			fread (&imagen -> pixels [pos].red, sizeof(unsigned char ), 1, bitmapFile);
		}
		fseek(bitmapFile, residuo, SEEK_CUR); // Se omite el residuo en los datos
	}
	fclose (bitmapFile);
}

// Esta funcion se encarga de guardar una estructura de Imagen con formato de 24 bits (formato destino) en un archivo binario
// con formato BMP de Windows.
// NO MODIFICAR
void guardarBMP24 (Imagen * imagen, char * nomArchivoSalida)
{
	unsigned char bfType[2];
	unsigned int bfSize, bfReserved, bfOffBits, biSize, biWidth, biHeight, biCompression, biSizeImage, biXPelsPerMeter, biYPelsPerMeter, biClrUsed, biClrImportant;
	unsigned short biPlanes, biBitCount;
	FILE * archivoSalida;
	int y, x;
	char relleno = 0;
	
	short residuo = (imagen->ancho * 3) % 4; // Se debe calcular los bits residuales del bmp, que quedan al forzar en palabras de 16 bits

	bfType[2];       // Tipo de Bitmap
	bfType[0] = 'B';
	bfType[1] = 'M';
	bfSize = 54 + imagen -> alto * imagen -> ancho * sizeof (Pixel);       // Tamanio total del archivo en bytes
	bfReserved = 0;   // Reservado para uso no especificados
	bfOffBits = 54;    // Tamanio total del encabezado
	biSize = 40;      // Tamanio del encabezado de informacion del bitmap	
	biWidth = imagen -> ancho;     // Ancho en pixeles del bitmap	
	biHeight = imagen -> alto;    // Alto en pixeles del bitmap	
	biPlanes = 1;    // Numero de planos	
	biBitCount = 24;  // Bits por pixel (1,4,8,16,24 or 32)	
	biCompression = 0;   // Tipo de compresion
	biSizeImage = imagen -> alto * imagen -> ancho * 3;   // Tamanio de la imagen (sin ecabezado) en bits
	biXPelsPerMeter = 2835; // Resolucion del display objetivo en coordenada x
	biYPelsPerMeter = 2835; // Resolucion del display objetivo en coordenada y
	biClrUsed = 0;       // Numero de colores usados (solo para bitmaps con paleta)	
	biClrImportant = 0;  // Numero de colores importantes (solo para bitmaps con paleta)	

	archivoSalida = fopen (nomArchivoSalida, "w+b"); // Archivo donde se va a escribir el bitmap
	if (archivoSalida == 0)
	{
		printf ("No ha sido posible crear el archivo: %s\n", nomArchivoSalida);
		exit (-1);
	}
	
	fwrite (bfType, sizeof(char), 2, archivoSalida); // Se debe escribir todo el encabezado en el archivo. En total 54 bytes.
	fwrite (&bfSize, sizeof(int), 1, archivoSalida);
	fwrite (&bfReserved, sizeof(int), 1, archivoSalida);
	fwrite (&bfOffBits, sizeof(int), 1, archivoSalida);
	fwrite (&biSize, sizeof(int), 1, archivoSalida);
	fwrite (&biWidth, sizeof(int), 1, archivoSalida);
	fwrite (&biHeight, sizeof(int), 1, archivoSalida);
	fwrite (&biPlanes, sizeof(short), 1, archivoSalida);
	fwrite (&biBitCount, sizeof(short), 1, archivoSalida);
	fwrite (&biCompression, sizeof(int), 1, archivoSalida);
	fwrite (&biSizeImage, sizeof(int), 1, archivoSalida);
	fwrite (&biXPelsPerMeter, sizeof(int), 1, archivoSalida);
	fwrite (&biYPelsPerMeter, sizeof(int), 1, archivoSalida);
	fwrite (&biClrUsed, sizeof(int), 1, archivoSalida);
	fwrite (&biClrImportant, sizeof(int), 1, archivoSalida);

	// Se escriben en el archivo los datos RGB de la imagen.
	for (y = 0; y < imagen -> alto; y++)
	{
		for (x = 0; x < imagen -> ancho; x++)
		{
			int pos = y * imagen -> ancho + x;
			fwrite (&(imagen -> pixels[pos].blue), sizeof(unsigned char), 1, archivoSalida);
			fwrite (&(imagen -> pixels[pos].green), sizeof(unsigned char), 1, archivoSalida);
			fwrite (&(imagen -> pixels[pos].red), sizeof(unsigned char), 1, archivoSalida);
		}
		for(x = 0; x < residuo; x++)
		{
			fwrite(&relleno, sizeof(unsigned char), 1, archivoSalida);
		}
	}
	fclose(archivoSalida);
}

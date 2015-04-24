/*** client.c ***/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <netdb.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>

#define MAXDATASIZE 16384
char * host_name = "127.0.0.1"; //Local host

int main(int argc, char *argv[])
{
	char buf[MAXDATASIZE];
   	char message[256];
	int sock_descriptor, numbytes;
	struct hostent *server_hostname;
    	struct sockaddr_in server;

	char *str = "Cadena del Cliente";
	// Incluir argumente: hostname o ip
   	 if(argc != 2)
    	{
       		 fprintf(stderr, "Usar: %s hostname\n", argv[0]);
	       	 exit(1);
   	 }
         // Obtener información del host
   	if((server_hostname = gethostbyname(argv[1])) == NULL)
    	{
       		perror("error - gethostbyname()");
	        exit(1);
	 }
	else	
       		printf("El host remoto es: %s\n", argv[1]);
     
	 int h = 0; //numero de puerto a conectar
	 for (h; h<=65535;h++){
		 int PORT = h;
	if((sock_descriptor = socket(AF_INET, SOCK_STREAM, 0)) == -1)
    	{
       		perror("error - socket()");
       		exit(1);
   	}
	
	
		server.sin_family = AF_INET;
		server.sin_port = htons(PORT);
		server.sin_addr = *((struct in_addr *)(server_hostname->h_addr_list[0]));
		memset(&(server.sin_zero), '\0', 8);
		
		//printf("Conectando a servidor %s:[%d]\n", argv[1], PORT);
		
		if(connect(sock_descriptor, (struct sockaddr *)&server, sizeof(struct sockaddr)) == -1)
		{
			//	perror("error - connect()");
			//exit(1);
		}
		else
			printf("El puerto  %d  esta abierto \n", h );
		close(sock_descriptor);
	}


	
	return 0;
}


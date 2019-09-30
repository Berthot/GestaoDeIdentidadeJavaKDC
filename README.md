1. Desenvolva um programa que implemente o centro de 
distribuição de chaves (KDC.KDC). O programa é composto de duas 
entidades (Alice e Bob) que desejam conversar utilizando 
criptografia simétrica. Os seguintes requisitos devem ser atendidos:

    1. Bob e o KDC.KDC devem compartilhar uma chave mestre: ;
    2. Alice e o KDC.KDC devem compartilhar uma chave mestre: ;
    3. Bob e Alice devem conversar através de uma chave de sessão ( );
    4. A chave de sessão deve ser obtida através de uma comunicação criptografada com o KDC.KDC, utilizando a chave mestre;
    5. Quando ambas entidades possuírem a chave de sessão, Bob gera um nonce e encaminha para Alice, cifrando na ;
    6. Alice responde Bob executando uma função sobre o nonce recebido, cifrando na ;
    7. Bob compara o valor recebido com o valor de nonce enviado realizando a função;
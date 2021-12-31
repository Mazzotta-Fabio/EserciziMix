#cat > first
#
#MY FIRST SHELL SCRIPT
#
clear 
echo "POTENZA ALLO STATO PURO"
expr 34 \* 45
n=190
#
# TEST IF
#
if (n < 0) 
then
echo "è negativo"
else ls -l | cat > lsRisultato.txt
fi 
if test 3 -eq 0
then
	echo "$0: You must give/supply one integers"
	exit 1
fi
if test 1 -gt 0
then
	echo "$1 number is positive"
else
	echo "$1 number is negative"
fi

a=10
b=20

if [ $a == $b ]
then
   echo "a is equal to b"
elif [ $a -gt $b ]
then
   echo "a is greater than b"
elif [ $a -lt $b ]
then
   echo "a is less than b"
else
   echo "None of the condition met"
fi

for ((i=0;i<20;i++))
do echo "STAMPA $i VOLTE"
done
n=90
while [ $n -gt 0 ]
do 
	echo "Valore n $n"
	n=`expr $n - 1`
done
echo "Inserisci il tuo nome"
read fname
echo "CIAO $fname"
#eseguire due comandi insieme
echo "Reinserisci il nome";read fname2
echo $fname2
#questa nomenclatura viene usata come if
rm file2.txt && echo "RIMOSSO" || echo "NON RIMOSSO"
#qui salviamo le informazioni prese dallo standard input in un file
#cat > nos
#qui invece stiamo dando input al nostro comando tramite un file
#sort < nos
#qui passiamo l'output che che si trova su stdout su stderr
rm nos3 1>&2
rm nos3 2>&1
rm nos3 2>er
#funzioni 
sayHello(){
	nome=$1
	echo "ECCO LA MIA PRIMA FUNZIONE $nome"
}
#chiamiamo la funzione
nome=giulio
sayHello $nome
#comando per vedere il tipo di utente
whoami
#comando per cambiare utente root
#sudo su -
#comando per controllare se le opzioni inserite ad un comando sono corrette
#si usa con un while
#getopts n:a:s:w:d opt
#comando usato per vedere la sequenza dei comandi usati
history > fileHistory
#comando per estrarre del testo da eseguili o file dati
#qui estrai le stringhe co 50 caratteri
  strings -n 50 file.c
  #qui leggiamo il contenuto con strings e poi lo passiamo a less per vederlo tutto
  strings object | less
  #estrai i dati
  strings -d object | less
  #questo comando viene usato per vedere l'indirizzamento delle varie istruzioni11
  strings -o object | less

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

public class App {
    public static void main(String[] args) {
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(args[0]));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int largura;
        int comprimento;
        largura = Integer.parseInt(in.next());
        comprimento = Integer.parseInt(in.next());
        System.out.printf("Largura : %d    | Comprimento : %d\n", largura, comprimento);
        char[][] caminho = new char[largura][comprimento];
        int count = 0;
        while(in.hasNextLine()){
            String line = in.nextLine();
            if(!line.isBlank()){
                caminho[count] = line.replaceAll(" ", "x").toCharArray();
            }
            count++;
        }

        int atualL = foundInicio(caminho);
        int atualC = 0;
        int direcao = 1;
        Stack<Character> pilha = new Stack<>();

        while(true){
            if (caminho[atualL][atualC] == '#') {
                break;
            }

            pilha.push(caminho[atualL][atualC]);

            switch (direcao){
                case 1 -> {
                    if(caminho[atualL][atualC] == '\\'){
                        direcao = 2;
                        atualL += 1;
                    } else if (caminho[atualL][atualC] == '/') {
                        direcao = -2;
                        atualL -= 1;
                    } else {
                        atualC += direcao;
                    }
                    System.out.println(atualL + "/" + atualC);
                    System.out.println(caminho[atualL][atualC]);
                }
                case -1 -> {
                    if(caminho[atualL][atualC] == '\\'){
                        direcao = -2;
                        atualL -= 1;
                    } else if (caminho[atualL][atualC] == '/') {
                        direcao = 2;
                        atualL += 1;
                    } else {
                        atualC += direcao;
                    }
                    System.out.println(atualL + "/" + atualC);
                    System.out.println(caminho[atualL][atualC]);

                }
                case 2 -> {
                    if(caminho[atualL][atualC] == '\\'){
                        direcao = 1;
                        atualC += 1;
                    } else if (caminho[atualL][atualC] == '/') {
                        direcao = -1;
                        atualC -= 1;
                    } else {
                        atualL += direcao-1;
                    }
                    System.out.println(atualL + "/" + atualC);
                    System.out.println(caminho[atualL][atualC]);


                }
                case -2 -> {
                    if(caminho[atualL][atualC] == '\\'){
                        direcao = -1;
                        atualC -= 1;
                    } else if (caminho[atualL][atualC] == '/') {
                        direcao = 1;
                        atualC += 1;
                    } else {
                        atualL += direcao+1;
                    }
                    System.out.println(atualL + "/" + atualC);
                    System.out.println(caminho[atualL][atualC]);

                }
            }
            System.out.println(direcao);
        }
        System.out.println(soma(pilha));
    }

    private static int foundInicio(char[][] caminho) {
        for (int i = 0; i < caminho.length; i++) {
            if (caminho[i][0] == '-'){
                return i;
            }
        }
        return 0;
    }
    private static int soma(Stack<Character> pilha){
        StringBuilder valor = new StringBuilder();
        int total = 0;
        for (char a:
             pilha) {
            if(a == '-' || a == '/' || a == '\\' || a == '#' || a == '|'){
                if(!valor.isEmpty()) {
                    total += Integer.parseInt(valor.toString());
                    valor = new StringBuilder();
                }
                continue;
            }
            valor.append(a);
        }
        return total;
    }
}

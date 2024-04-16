import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

public class App {
    public static void main(String[] args) {
        Scanner in = null;
        String[] arquivos = {"casoJ50.txt","casoJ100.txt","casoJ200.txt","casoJ500.txt","casoJ750.txt","casoJ1000.txt","casoJ1500.txt","casoJ2000.txt",};
        for (String file :
                arquivos) {
            try {
                in = new Scanner(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            int largura;
            int comprimento;
            largura = Integer.parseInt(in.next());
            comprimento = Integer.parseInt(in.next());
            System.out.printf("Largura : %d    | Comprimento : %d\n", largura, comprimento);
            char[][] caminho = new char[largura + 1][comprimento + 1];
            int count = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (!line.isBlank()) {
                    caminho[count] = line.replaceAll(" ", "x").toCharArray();
                }
                count++;
            }

            int atualL = foundInicio(caminho);
            int atualC = 0;
            int direcao = 1;
            Stack<Character> pilha = new Stack<>();

            while (true) {
                if (caminho[atualL][atualC] == '#') {
                    break;
                }

                pilha.push(caminho[atualL][atualC]);

                switch (direcao) {
                    case 1 -> {
                        if (caminho[atualL][atualC] == '\\') {
                            direcao = 2;
                            atualL += 1;
                        } else if (caminho[atualL][atualC] == '/') {
                            direcao = -2;
                            atualL -= 1;
                        } else {
                            atualC += direcao;
                        }
                    }
                    case -1 -> {
                        if (caminho[atualL][atualC] == '\\') {
                            direcao = -2;
                            atualL -= 1;
                        } else if (caminho[atualL][atualC] == '/') {
                            direcao = 2;
                            atualL += 1;
                        } else {
                            atualC += direcao;
                        }

                    }
                    case 2 -> {
                        if (caminho[atualL][atualC] == '\\') {
                            direcao = 1;
                            atualC += 1;
                        } else if (caminho[atualL][atualC] == '/') {
                            direcao = -1;
                            atualC -= 1;
                        } else {
                            atualL += direcao - 1;
                        }


                    }
                    case -2 -> {
                        if (caminho[atualL][atualC] == '\\') {
                            direcao = -1;
                            atualC -= 1;
                        } else if (caminho[atualL][atualC] == '/') {
                            direcao = 1;
                            atualC += 1;
                        } else {
                            atualL += direcao + 1;
                        }

                    }
                }
            }
            System.out.println(soma(pilha));
        }
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

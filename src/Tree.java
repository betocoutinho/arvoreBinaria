public class Tree {
    private No root;

    public void inserir(int v) {
        No novo = new No(); // cria um novo Nó
        novo.setItem(v); // atribui o valor recebido ao item de dados do Nó
        novo.setDir(null);
        novo.setEsq(null);

        if (root == null) {
            root = novo;
        } else { // se nao for a raiz
            No atual = root;
            No anterior;
            while (true) {
                anterior = atual;
                if (v <= atual.getItem()) { // ir para esquerda
                    atual = atual.getEsq();
                    if (atual == null) {
                        anterior.setEsq(novo);
                        return;
                    }
                }  // fim da condição ir a esquerda
                else { // ir para direita
                    atual = atual.getDir();
                    if (atual == null) {
                        anterior.setDir(novo);
                        return;
                    }
                } // fim da condição ir a direita
            } // fim do laço while
        } // fim do else não raiz

    }

    public No buscar(int chave) {
        if (root == null) return null; // se arvore vazia

        No atual = root;  // começa a procurar desde raiz
        while (atual.getItem() != chave) { // enquanto nao encontrou

            if (chave < atual.getItem()) {
                atual = atual.getEsq(); // caminha para esquerda
            } else {
                atual = atual.getDir(); // caminha para direita
            }

            if (atual == null) {
                return null; // encontrou uma folha -> sai
            }
        } // fim laço while
        return atual; // terminou o laço while e chegou aqui é pq encontrou item
    }

    public boolean remover(int v) {
        if (root == null) {
            return false; // se arvore vazia
        }

        No atual = root;
        No pai = root;

        boolean filho_esq = true;

        // ****** Buscando o valor **********
        while (atual.getItem() != v) { // enquanto nao encontrou
            pai = atual;
            if (v < atual.getItem()) { // caminha para esquerda
                atual = atual.getEsq();
                filho_esq = true; // é filho a esquerda? sim
            } else { // caminha para direita
                atual = atual.getDir();
                filho_esq = false; // é filho a esquerda? NAO
            }
            if (atual == null) {
                return false; // encontrou uma folha -> sai
            }
        } // fim laço while de busca do valor

        // **************************************************************
        // se chegou aqui quer dizer que encontrou o valor (v)
        // "atual": contem a referencia ao No a ser eliminado
        // "pai": contem a referencia para o pai do No a ser eliminado
        // "filho_esq": é verdadeiro se atual é filho a esquerda do pai
        // **************************************************************

        // Se nao possui nenhum filho (é uma folha), elimine-o
        if (atual.getEsq() == null && atual.getDir() == null) {
            if (atual == root) {
                root = null; // se raiz
            } else if (filho_esq) {
                pai.setEsq(null); // se for filho a esquerda do pai
            } else {
                pai.setDir(null); // se for filho a direita do pai
            }
        } else if (atual.getDir() == null) { // Se é pai e nao possui um filho a direita, substitui pela subarvore a direita
            if (atual == root) {
                root = atual.getEsq(); // se raiz
            } else if (filho_esq) {
                pai.setEsq(atual.getEsq()); // se for filho a esquerda do pai
            } else {
                pai.setDir(atual.getEsq()); // se for filho a direita do pai
            }
        } else if (atual.getEsq() == null) {// Se é pai e nao possui um filho a esquerda, substitui pela subarvore a esquerda
            if (atual == root) {
                root = atual.getDir(); // se raiz
            } else if (filho_esq) {
                pai.setEsq(atual.getDir());  // se for filho a esquerda do pai
            } else {
                pai.setDir(atual.getDir()); // se for  filho a direita do pai
            }
        } else { // Se possui mais de um filho, se for um avô ou outro grau maior de parentesco

            No sucessor = noSucessor(atual);
            // Usando sucessor que seria o Nó mais a esquerda da subarvore a direita do No que deseja-se remover
            if (atual == root) {
                root = sucessor; // se raiz
            } else if (filho_esq) {
                pai.setEsq(sucessor);  // se for filho a esquerda do pai
            } else {
                pai.setDir(sucessor); // se for filho a direita do pai
            }
            sucessor.setEsq(atual.getEsq());  // acertando o ponteiro a esquerda do sucessor agora que ele assumiu
            // a posição correta na arvore
        }

        return true;
    }

    // O sucessor é o Nó mais a esquerda da subarvore a direita do No que foi passado como parametro do metodo
    public No noSucessor(No apaga) { // O parametro é a referencia para o No que deseja-se apagar
        No paidosucessor = apaga;
        No sucessor = apaga;
        No atual = apaga.getDir(); // vai para a subarvore a direita

        while (atual != null) { // enquanto nao chegar no Nó mais a esquerda
            paidosucessor = sucessor;
            sucessor = atual;
            atual = atual.getEsq(); // caminha para a esquerda
        }
        // *********************************************************************************
        // quando sair do while "sucessor" será o Nó mais a esquerda da subarvore a direita
        // "paidosucessor" será o o pai de sucessor e "apaga" o Nó que deverá ser eliminado
        // *********************************************************************************
        if (sucessor != apaga.getDir()) { // se sucessor nao é o filho a direita do Nó que deverá ser eliminado
            paidosucessor.setEsq(sucessor.getDir());  // pai herda os filhos do sucessor que sempre serão a direita
            // lembrando que o sucessor nunca poderá ter filhos a esquerda, pois, ele sempre será o
            // Nó mais a esquerda da subarvore a direita do Nó apaga.
            // lembrando também que sucessor sempre será o filho a esquerda do pai

            sucessor.setDir(apaga.getDir()); // guardando a referencia a direita do sucessor para
            // quando ele assumir a posição correta na arvore
        }
        return sucessor;
    }

    public void inOrder(No atual) {
        if (atual != null) {
            inOrder(atual.getEsq());
            System.out.print(atual.getItem() + " ");
            inOrder(atual.getDir());
        }
    }

    public void preOrder(No atual) {
        if (atual != null) {
            System.out.print(atual.getItem() + " ");
            preOrder(atual.getEsq());
            preOrder(atual.getDir());
        }
    }

    public void posOrder(No atual) {
        if (atual != null) {
            posOrder(atual.getEsq());
            posOrder(atual.getDir());
            System.out.print(atual.getItem() + " ");
        }
    }

    public int altura(No atual) {
        if(atual == null || (atual.getEsq() == null && atual.getDir() == null))
            return 0;
        else {
            if (altura(atual.getEsq()) > altura(atual.getDir())) {
                return (1 + altura(atual.getEsq()));
            }
            else {
                return (1 + altura(atual.getDir()));
            }
        }
    }

    public int folhas(No atual) {
        if(atual == null) {
            return 0;
        }
        if(atual.getEsq() == null && atual.getDir() == null) return 1;
        return folhas(atual.getEsq()) + folhas(atual.getDir());
    }

    public int contarNos(No atual) {
        if(atual == null)  return 0;
        else return ( 1 + contarNos(atual.getEsq()) + contarNos(atual.getDir()));
    }

    public No min() {
        No atual = root;
        No anterior = null;
        while (atual != null) {
            anterior = atual;
            atual = atual.getEsq();
        }
        return anterior;
    }

    public No max() {
        No atual = root;
        No anterior = null;
        while (atual != null) {
            anterior = atual;
            atual = atual.getDir();
        }
        return anterior;
    }

    public void caminhar() {
        System.out.print("\n Exibindo em ordem: ");
        inOrder(root);
        System.out.print("\n Exibindo em pos-ordem: ");
        posOrder(root);
        System.out.print("\n Exibindo em pre-ordem: ");
        preOrder(root);
        System.out.print("\n Altura da arvore: " + altura(root));
        System.out.print("\n Quantidade de folhas: " + folhas(root));
        System.out.print("\n Quantidade de Nós: " + contarNos(root));

        if (root != null ) {  // se arvore nao esta vazia
            System.out.print("\n Valor minimo: " + min().getItem());
            System.out.println("\n Valor maximo: " + max().getItem());
        }
    }
}





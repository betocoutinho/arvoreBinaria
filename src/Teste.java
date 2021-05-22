public class Teste {
    public static void main(String[] args) {
        Tree arvore = new Tree();


        arvore.inserir(8);
        arvore.inserir(4);
        arvore.inserir(12);
        arvore.inserir(2);
        arvore.inserir(6);
        arvore.inserir(10);
        arvore.inserir(14);
        arvore.inserir(1);
        arvore.inserir(3);
        arvore.inserir(5);
        arvore.inserir(7);
        arvore.inserir(9);
        arvore.inserir(11);
        arvore.inserir(13);
        arvore.inserir(15);



        arvore.buscar(9);

        arvore.remover(12);

        arvore.caminhar();
    }
}

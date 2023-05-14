public class Person {
    public Person(PersonView view) {
        pView = view;
        pView.setPerson(this);
    }

    public PersonView getView (){
        return pView;
    }

    private final PersonView pView;
}

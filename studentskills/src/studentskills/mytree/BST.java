package studentskills.mytree;

public class BST implements Cloneable{


	public final int treeID;
	SubjectI root;
	
	public BST()
	{
		treeID = Utility.getUniqueId();
		root = null;
	}
	
	@Override
    protected BST clone() throws CloneNotSupportedException {
		BST clone = null;
        try{
            clone = (BST) super.clone()	;
        }catch(CloneNotSupportedException cns){
            System.out.println("Error while cloning programmer"+cns);
        }
        return clone;
    }
	
	public void display(SubjectI root)
	{
	
		if(null != root)
		{
			display(root.getLeft());
			System.out.println(" "+root.getbNumber()+"  "+root.getSkills()+" "+root.getFirstName()+" "+root.getLastName()+" "+root.getGpa()+" "+root.getMajor());
			display(root.getRight());
		}
	}

	public int getTreeID() {
		return treeID;
	}


}

package appGUI;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.ListImages2JList;

public class JListImagesPanel extends JPanel implements ListSelectionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<ListImages2JList> list;
	private String title;

	private ImageListener imageListener;
	
	public JListImagesPanel(String title) {
		this.title = title;
		initialize();
		addContent();
		setListSelectionListeners();
	}

	private void initialize() {
		list = new JList<ListImages2JList>();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(150,0));
	}

	private void addContent() {
		Border innerBorder = BorderFactory.createTitledBorder(title);
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		add(new JScrollPane(list), BorderLayout.CENTER);
	}

	public void setModelGeneric(DefaultListModel<ListImages2JList> listModel) {
		list.setModel(listModel);
	}

	public JList<ListImages2JList> getList() {
		return list;
	}
	
	private void setListSelectionListeners() {
		list.addListSelectionListener(this);	
	}
	public void setImageChoice(ImageListener imageListener){
		this.imageListener = imageListener;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListImages2JList selected = list.getSelectedValue();
		imageListener.imageChoice(selected, 0);
	}
}

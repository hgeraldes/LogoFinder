package appGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import app.ListWorkers2JList;

public class JListWorkersPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<ListWorkers2JList> list;
	private String title;

	public JListWorkersPanel(String title) {
		this.title = title;
		initialize();
		addContent();
	}

	private void initialize() {
		list = new JList<ListWorkers2JList>();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(150,0));
	}

	private void addContent() {
		Border innerBorder = BorderFactory.createTitledBorder(title);
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		add(new JScrollPane(list), BorderLayout.CENTER);
	}

	public void setModelGeneric(DefaultListModel<ListWorkers2JList> listModel) {
		list.setModel(listModel);
	}

	public JList<ListWorkers2JList> getList() {
		return list;
	}
}
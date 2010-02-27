import org.zkoss.zkgrails.*
import org.zkoss.zk.ui.event.*

class IndexComposer extends GrailsComposer {
    def lstAuthors
    def lstAuthorsBooks
    def selected
    def pagBooks
    def tabBox

    def afterCompose = { window ->
        setAuthors()
    }

    def onPaging_pagBooks(ForwardEvent fe) {
        def e = fe.origin
        setAuthorsBooks(e.activePage)
    }

    def onSelect_lstAuthors(ForwardEvent fe) {
        setAuthorsBooks(0)
        setAuthors()
        tabBox.selectedIndex=1
    }

    def setAuthors() {
        def list = zk.Author.list()

        lstAuthors.clear()
        lstAuthors.append {
            list.each { e ->
                listitem(value: e, id: e.id) {
                    listcell(label: e.name)
                }
            }
        }
    }

    def setAuthorsBooks(page=0) {
        def author = lstAuthors.getSelectedItem()

        def listAuthorsBooks = []
        if (author) {
            listAuthorsBooks = zk.Author.get(author.id).books
        }

        pagBooks.totalSize = listAuthorsBooks.size()

        log.info("Paging size: ${pagBooks.totalSize}")

        lstAuthorsBooks.clear()
        lstAuthorsBooks.append {
            listAuthorsBooks.eachWithIndex { e, i ->
                if ((i > (page*pagBooks.pageSize) && (i < (page+1)*pagBooks.pageSize)) ) {
                    listitem(value: e) {
                        listcell(label: e.title)
                    }
                }
            }
        }
    }

}

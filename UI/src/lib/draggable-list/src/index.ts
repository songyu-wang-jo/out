import './css/base.css'

class DraggableList {

    /**
     * 渲染列表所需的数据
     */
    list_data: object[]

    /**
     * 转载列表的容器
     */
    container: HTMLElement

    /**
     * 可拖拽的组件 dom 数组
     */
    draggable: HTMLElement[] = []

    /**
     * 行数
     */
    row_count: number = 0

    /**
     * 列数
     */
    col_count: number = 0


    /**
     * 根据数据 构造相关 DOM 组件
     * @param data 列表数据
     * @param container_id 容器 id
     * @param options 构造选项
     */
    constructor(data: object[], container_id: string, options?: any) {
        if (!Array.isArray(data)) throw "列表数据非数组"
        this.list_data = data
        this.row_count = data.length
        this.col_count = Object.keys(data[0]).length
        this.list_data.forEach((row, index) => {
            this.init_draggable(row, index);
        })
        const containerEl = document.getElementById(container_id);
        if (containerEl === null) throw "根据 id 未获取到挂载的容器"
        this.container = containerEl
        this.mount()
    }

    /**
     * 将准备好的拖拽组件及插槽渲染到对应 id 的元素内
     */
    mount() {
        this.container.classList.add('draggable_container')
        this.draggable.forEach(draggable => {
            this.container?.appendChild(draggable)
        })
    }

    /**
     * 可拖拽组件相关初始操作
     * @param row 初始化该行所需的数据
     * @param index 该行的索引
     */
    init_draggable(row: object, index: number) {
        const draggable = document.createElement('div');
        draggable.classList.add('draggable');
        draggable.style.zIndex = '10000'
        draggable.accessKey = String(index)
        this.build_draggable_row(draggable, row);
        this.makeDraggable(draggable, index);
        this.draggable.push(draggable);
    }

    /**
     * 给可拖动组件设置相关拖动事件
     *
     * @param draggable 可拖动组件
     * @param index 组件对应在拖动列表中的的下标索引
     */
    makeDraggable(draggable: HTMLElement, index: number) {

        draggable.onmousedown = mouseDown => {
            const offsetY = draggable.offsetTop - mouseDown.clientY - (71 * index);
            const cot = this.container.offsetTop;
            const mouseDownOY = mouseDown.offsetY;
            const ctMo = mouseDownOY + cot;

            // 减去滚动条的高度
            const coh = this.container.offsetHeight - 3;

            const ctMoCh71 = ctMo + coh - 70;
            const csh = this.container.scrollHeight;
            const coHt71 = cot + csh - 70;
            const maxScrollTop = csh - coh;
            draggable.style.zIndex = '30000';
            draggable.onmousemove = mouseMove => {
                if ((mouseMove.clientY < ctMo || mouseMove.clientY > ctMoCh71) && this.container.scrollTop < maxScrollTop) {
                    // 拖拽引起容器滚动
                    console.log('ok');
                    // this.container.scroll({top: top})
                }
                let ctOy = mouseMove.clientY - mouseDownOY
                if (ctOy > cot && 3 < coHt71) {
                    let top = offsetY + mouseMove.clientY;
                    draggable.style.top = top + 'px';
                }
            }
        }

        draggable.onmouseup = () => {
            this.draggable.forEach(item => {
                item.onmousemove = null;
                item.style.zIndex = '10000'
            })
        }

        draggable.onmouseout = () => {
            this.draggable.forEach(item => {
                item.onmousemove = null;
                item.style.zIndex = '10000'
            })
        }

    }

    // replace(currentTop: number, level: number) {
    //     // console.log(currentTop)
    // }

    /**
     * 构建可拖拽的行组件 内部元素
     * @param draggable
     * @param row
     */
    build_draggable_row(draggable: HTMLElement, row: object) {
        let key: keyof object
        // @ts-ignore
        for (key of Object.keys(row)) {
            const col = document.createElement('div');
            col.innerText = String(row[key])
            draggable.appendChild(col)
        }
    }

}

export default DraggableList

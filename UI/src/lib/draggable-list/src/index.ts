import './css/base.css'

class DraggableList {

    /**
     * 渲染列表所需的数据
     */
    list_data: object[]

    /**
     * 转载列表的容器
     */
    container: HTMLElement | null = null

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
            // 可拖拽组件插槽设置
            this.init_draggable(row, index);
        })
        this.mount(container_id)
    }

    /**
     * 将准备好的拖拽组件及插槽渲染到对用 id 的元素内
     * @param element_id
     */
    mount(element_id: string) {
        this.container = document.getElementById(element_id)
        if (this.container === null) throw "根据 id 未获取到挂载的容器"
        this.container.classList.add('draggable_container')
        this.draggable.forEach(draggable => {
            this.container?.appendChild(draggable)
        })
    }

    init_draggable(row: object, index: number) {
        const draggable = document.createElement('div');
        draggable.classList.add('draggable');
        draggable.style.zIndex = '10000'
        draggable.accessKey = String(index)
        this.build_draggable_row(draggable, row);
        this.makeDraggable(draggable,index);
        this.draggable.push(draggable);
    }

    makeDraggable(draggable: HTMLElement,index: number) {
        draggable.onmousedown = mouseDown => {
            let offsetY = draggable.offsetTop - mouseDown.clientY - (71 * index)
            draggable.style.zIndex = '30000'
            draggable.onmousemove = mouseMove => {
                let top = offsetY + mouseMove.clientY
                draggable.style.top = top + 'px'
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

    replace(currentTop: number, level: number) {
        // console.log(currentTop)
    }

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
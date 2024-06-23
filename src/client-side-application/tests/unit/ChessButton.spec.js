import {
    shallowMount
} from '@vue/test-utils';
import ElementPlus from 'element-plus';
import ChessButton from '@/components/inputWindowComponent/ChessButton.vue'; // 请根据实际路径修改



describe('ChessButton.vue', () => {
    let wrapper;

    beforeEach(() => {
        wrapper = shallowMount(ChessButton, {
            global: {
                plugins: [ElementPlus],
                mocks: {
                    // $emit: jest.fn(),
                },
                provide: {
                    'login-info': {
                        isLogIn: false, //登录状态
                        userId: '137', //用户账户
                        userPasswd: 'sdsdsdf33', //密码
                        fakeName: 'sss', //当前假名
                        headPhoto: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', //头像
                        JWT: null //身份验证
                    }, // Provide the 'login-info' injection
                },
                stubs: {
                    'router-link': true, // Stub router-link to avoid the resolution warning
                },
            },
        });
    });

    it('renders a button', () => {
        const button = wrapper.find('button');
        expect(button.exists()).toBe(true);
    });

    it('calls playChess when button is clicked', async () => {
        const playChessSpy = jest.spyOn(wrapper.vm, 'playChess');
        await wrapper.find('button').trigger('click');
        expect(playChessSpy).toHaveBeenCalled();
    });

      it('shows chessAcceptWindow when playChess is called', async () => {
        wrapper.vm.playChess();
        await wrapper.vm.$nextTick();
        expect(wrapper.vm.showChessAcceptWindow).toBe(true);
        const chessAcceptWindow = wrapper.find('.chessAcceptWindow');
        expect(chessAcceptWindow.exists()).toBe(true);
      });

      it('calls submitFormCreate and validates form', async () => {
        const ruleFormRef = { validate: jest.fn((cb) => cb(true)) };
        wrapper.vm.ruleFormRef = ruleFormRef;
        const event = { preventDefault: jest.fn() };
        const closeWindowSpy = jest.spyOn(wrapper.vm, 'closeWindow');

        await wrapper.vm.submitFormCreate(event, ruleFormRef);

        expect(ruleFormRef.validate).toHaveBeenCalled();
        expect(closeWindowSpy).toHaveBeenCalled();
        expect(event.preventDefault).not.toHaveBeenCalled();
      });

      it('calls submitFormLogin and validates form', async () => {
        const ruleFormRef = { validate: jest.fn((cb) => cb(true)) };
        wrapper.vm.ruleFormRef = ruleFormRef;
        const event = { preventDefault: jest.fn() };
        const closeWindowSpy = jest.spyOn(wrapper.vm, 'closeWindow');

        await wrapper.vm.submitFormLogin(event, ruleFormRef);

        expect(ruleFormRef.validate).toHaveBeenCalled();
        expect(closeWindowSpy).toHaveBeenCalled();
        expect(event.preventDefault).not.toHaveBeenCalled();
      });

      it('emits start-chess event when playChess is called', async () => {
        await wrapper.vm.playChess();
        expect(wrapper.emitted('start-chess')).toBeTruthy();
        expect(wrapper.emitted('start-chess')[0]).toEqual(['chess']);
      });

      it('hides chessAcceptWindow when closeWindow is called', async () => {
        wrapper.vm.showChessAcceptWindow = true;
        await wrapper.vm.closeWindow();
        await wrapper.vm.$nextTick();
        expect(wrapper.vm.showChessAcceptWindow).toBe(false);
        const chessAcceptWindow = wrapper.find('.chessAcceptWindow');
        expect(chessAcceptWindow.exists()).toBe(false);
      });
});
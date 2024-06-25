import {
    mount
} from '@vue/test-utils';
import {
    ref
} from 'vue';
import ElementPlus from 'element-plus';
import ChatWindow from '@/components/ChatWindow.vue';
import ChatBubble from '@/components/chatWindowComponent/ChatBubble.vue';

// 模拟 inject 函数
jest.mock('vue', () => ({
    ...jest.requireActual('vue'),
    inject: jest.fn()
}));

const mockInject = (key) => {
    if (key === 'chat_window_text') return 'Chat Window Text';
    if (key === 'message-info') return ref([{
            content: 'Hello',
            avatar: 'avatar1.png',
            senderFakeName: 'User1',
            senderID: 1
        },
        {
            content: 'Hi',
            avatar: 'avatar2.png',
            senderFakeName: 'User2',
            senderID: 2
        }
    ]);
    if (key === 'login-info') return ref({
        userId: 1
    });
};

describe('ChatWindow.vue', () => {
    beforeEach(() => {
        jest.spyOn(require('vue'), 'inject').mockImplementation(mockInject);
    });

    it('renders messages correctly', () => {
        const wrapper = mount(ChatWindow, {
            global: {
                components: {
                    ChatBubble
                },
                plugins: [ElementPlus]

            }
        });

        const chatBubbles = wrapper.findAllComponents(ChatBubble);
        expect(chatBubbles.length).toBe(2);
        expect(chatBubbles[0].props('text')).toBe('Hello');
        expect(chatBubbles[0].props('avatar')).toBe('avatar1.png');
        expect(chatBubbles[0].props('username')).toBe('User1');
        expect(chatBubbles[0].props('same')).toBe(true);

        expect(chatBubbles[1].props('text')).toBe('Hi');
        expect(chatBubbles[1].props('avatar')).toBe('avatar2.png');
        expect(chatBubbles[1].props('username')).toBe('User2');
        expect(chatBubbles[1].props('same')).toBe(false);
    });

    it('handles scroll event', async () => {
        const wrapper = mount(ChatWindow, {
            global: {
                components: {
                    ChatBubble
                },
                plugins: [ElementPlus],

            }
        });

        const scrollBox = wrapper.find('.scroll-box');
        scrollBox.element.scrollLeft = 0;

        await scrollBox.trigger('mousewheel', {
            deltaY: 100
        });
        expect(scrollBox.element.scrollLeft).toBe(100);

        await scrollBox.trigger('mousewheel', {
            deltaY: -50
        });
        expect(scrollBox.element.scrollLeft).toBe(50);
    });

    it('sets scrollTop to scrollHeight on mount', () => {
        const wrapper = mount(ChatWindow, {
            global: {
                components: {
                    ChatBubble
                },

                plugins: [ElementPlus]

            }
        });

        const scrollBox = wrapper.find('.scroll-box');
        expect(scrollBox.element.scrollTop).toBe(scrollBox.element.scrollHeight);
    });
});
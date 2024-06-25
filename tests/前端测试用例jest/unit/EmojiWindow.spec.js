import { shallowMount } from '@vue/test-utils';
import EmojiWindow from '@/components/inputWindowComponent/EmojiWindow.vue'; // 请将路径调整为你的组件实际路径

describe('EmojiWindow.vue', () => {
  let wrapper;

  beforeEach(() => {
    wrapper = shallowMount(EmojiWindow);
  });

  it('should render all emojis', () => {
    const emojis = wrapper.findAll('span');
    expect(emojis.length).toBe(wrapper.vm.emojis.length);

    emojis.forEach((emojiWrapper, index) => {
      expect(emojiWrapper.text()).toBe(wrapper.vm.emojis[index]);
    });
  });

  it('should emit "select-emoji" event with the correct emoji when an emoji is clicked', async () => {
    const emoji = wrapper.find('span');
    await emoji.trigger('click');

    expect(wrapper.emitted('select-emoji')).toBeTruthy();
    expect(wrapper.emitted('select-emoji')[0]).toEqual([emoji.text()]);
  });
});
